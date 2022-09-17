package com.yologger.inapp_payment_nonconsumable_product

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.*

class BillingManager(
    private val activity: Activity,
    private val callback: Callback
) {
    interface Callback {
        fun onBillingManagerReady()
        fun onSuccess(purchase: Purchase)
        fun onFailure(errorCode: Int)
    }

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->

        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            Log.d("YOLO", "[${Thread.currentThread().name}] launchBillingFlow() succeeds")
            // 구매 성공
            for (purchase in purchases) {
                // 비소모품은 정상적으로 구매 완료 시 인정(Acknowledge)해야합니다.
                // 3일 이내 인정(Acknowledge)하지 않으면 자동으로 환불됩니다.
                handlePurchase(purchase)
            }

        } else {
            // 구매 실패
            Log.d("YOLO", "${Thread.currentThread().name}] launchBillingFlow() fails")
            callback.onFailure(billingResult.responseCode)
        }
    }

    private val billingClient = BillingClient.newBuilder(activity)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()


    init {
        billingClient.startConnection(object : BillingClientStateListener {

            override fun onBillingSetupFinished(billingResult: BillingResult) {

                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        Log.d("YOLO", "[${Thread.currentThread().name}] onBillingManagerReady() success")
                        callback.onBillingManagerReady()
                    }
                    else -> {
                        Log.d("YOLO", "[${Thread.currentThread().name}] onBillingManagerReady() failure")
                        callback.onFailure(billingResult.responseCode)
                    }
                }
            }

            override fun onBillingServiceDisconnected() {

            }
        })
    }

    fun querySkuDetails(
        type: String = BillingClient.SkuType.INAPP,
        vararg skuIDs: String,
        onResult: (List<SkuDetails>) -> Unit
    ) {
        Log.d("YOLO", "[${Thread.currentThread().name}] querySkuDetails()")

        val params = SkuDetailsParams.newBuilder()
            .setSkusList(skuIDs.toList())
            .setType(type)
            .build()

        billingClient.querySkuDetailsAsync(params) { billingResult: BillingResult, skuDetailsList: List<SkuDetails>? ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                Log.d("YOLO", "[${Thread.currentThread().name}] querySkuDetailsAsync() success")
                onResult(skuDetailsList ?: emptyList())
            } else {
                Log.d("YOLO", "[${Thread.currentThread().name}] querySkuDetailsAsync() failure")
                callback.onFailure(billingResult.responseCode)
            }
        }
    }

    fun purchase(skuDetails: SkuDetails) {

        Log.d("YOLO", "[${Thread.currentThread().name}] purchase()")

        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuDetails)
            .build()

        // 구매화면 표시
        val responseCode = billingClient.launchBillingFlow(activity, flowParams).responseCode
        // Go to PurchasesUpdatedListener

        if (responseCode != BillingClient.BillingResponseCode.OK) {
            // 구매 화면을 여는데 실패하면 이 부분이 호출
            callback.onFailure(responseCode)
        }
    }

    private fun handlePurchase(purchase: Purchase) {

        Log.d("YOLO", "[${Thread.currentThread().name}] handlePurchase()")
        Log.d("YOLO", "[${Thread.currentThread().name}] purchase: ${purchase}")

        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            Log.d("YOLO", "[${Thread.currentThread().name}] PurchaseState == PURCHASED")
            if (!purchase.isAcknowledged) {
                Log.d("YOLO", "[${Thread.currentThread().name}] purchase.isAcknowledged == false")
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        Log.d("YOLO", "[${Thread.currentThread().name}] acknowledgePurchase() succeed.")
                        callback.onSuccess(purchase)
                    } else {
                        Log.d("YOLO",  "[${Thread.currentThread().name}] acknowledgePurchase() fail. responseCode: ${billingResult.responseCode}")
                        callback.onFailure(billingResult.responseCode)
                    }
                }

//                var runnable = Runnable {
//                    billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
//                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
//                            Log.d("YOLO", "[${Thread.currentThread().name}] handlePurchase() succeed.")
//                            callback.onSuccess(purchase)
//                        } else {
//                            Log.d("YOLO",  "[${Thread.currentThread().name}] handlePurchase() fail. responseCode: ${billingResult.responseCode}")
//                            callback.onFailure(billingResult.responseCode)
//                        }
//                    }
//                }
//
//                var thread = Thread(runnable)
//                thread.start()
            } else {
                // 구매도 했고 인정(Acknowledge)도 했을 때
                Log.d("YOLO", "[${Thread.currentThread().name}] purchase.isAcknowledged == true")
            }
        } else {
            /*
                구매의 상태가 PURCHASED가 아닐 때
                Purchase.PurchaseState.PENDING // 디지털 상품 구매 후 결제는 현금으로 할 때
                Purchase.PurchaseState.UNSPECIFIED_STATE
             */
            Log.d("YOLO", "[${Thread.currentThread().name}] PurchaseState != PURCHASED")
            return
        }
    }

    fun checkIfPurchasedButNotAcknowledged(type: String = BillingClient.SkuType.INAPP) {

        Log.d("YOLO", "[${Thread.currentThread().name}] checkIfPurchasedButNotAcknowledged()")
        if (billingClient.isReady) {
            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient.isReady")
            billingClient.queryPurchasesAsync(type) { _: BillingResult, purchases: List<Purchase> ->
                if (purchases.isNotEmpty()) {
                    Log.d("YOLO", "[${Thread.currentThread().name}] purchases is not empty")
                    for (purchase in purchases) {
                        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                            if (!purchase.isAcknowledged) {
                                Log.d("YOLO", "[${Thread.currentThread().name}] Purchases is not acknowledged.")
                                handlePurchase(purchase)
                            } else {
                                Log.d("YOLO", "[${Thread.currentThread().name}] Purchases is acknowledged.")
                            }
                        } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                            Log.d(
                                "YOLO",
                                "${Thread.currentThread().name}] Purchases State = PENDING"
                            )
                        } else {
                            Log.d(
                                "YOLO",
                                "${Thread.currentThread().name}] Purchases State = UNSPECIFIED_STATE"
                            )
                        }
                    }
                } else {
                    Log.d("YOLO", "[${Thread.currentThread().name}] purchases is empty")
                }
            }
        } else {
            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient.isNotReady")
        }
    }

    fun checkIfAlreadyPurchased(
        sku: String,
        type: String = BillingClient.SkuType.INAPP,
        onResult: (isAlreadyPurchased: Boolean) -> Unit
    ) {
        Log.d("YOLO", "[${Thread.currentThread().name}] checkIfAlreadyPurchased()")

        billingClient.queryPurchasesAsync(type) { billingResult, purchasesList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                Log.d("YOLO", "[${Thread.currentThread().name}] queryPurchasesAsync() success")
                if (purchasesList.isNotEmpty()) {
                    Log.d("YOLO", "[${Thread.currentThread().name}] purchasesList is not empty")
                    for (purchase in purchasesList) {
                        Log.d("YOLO", "[${Thread.currentThread().name}] purchase: ${purchase.toString()}")
                        purchase.skus.forEach { it ->
                            Log.d("YOLO", "[${Thread.currentThread().name}] sku: ${it} from ${purchase.toString()}")
                            if (it == sku) {
                                return@forEach onResult(true)
                            }
                            return@forEach onResult(false)
                        }
                    }
                } else {
                    Log.d("YOLO", "[${Thread.currentThread().name}] purchasesList is empty")
                    return@queryPurchasesAsync onResult(false)
                }
            } else {
                Log.d("YOLO", "[${Thread.currentThread().name}] queryPurchasesAsync() fails")
                onResult(false)
            }
        }
    }


    fun endConnection() {
        billingClient.endConnection()
    }
}