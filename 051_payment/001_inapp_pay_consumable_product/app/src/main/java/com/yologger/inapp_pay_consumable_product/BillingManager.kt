package com.yologger.inapp_pay_consumable_product

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
            Log.d("YOLO", "[${Thread.currentThread().name}] launchBillingFlow() success")
            // 구매 성공
            for (purchase in purchases) {
                // 소모품은 구매 완료 시 소비(Consume)해야합니다.
                // 3일 이내 소비(Consume)하지 않으면 자동으로 환불됩니다.

                handlePurchase(purchase)
            }
        } else {
            Log.d("YOLO", "[${Thread.currentThread().name}] launchBillingFlow() failure")
            // 구매 실패
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
                        Log.d("YOLO", "[${Thread.currentThread().name}] startConnection() success")
                        callback.onBillingManagerReady()
                    }
                    else -> {
                        Log.d("YOLO", "[${Thread.currentThread().name}] startConnection() failure")
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

        Log.d("YOLO", "handlePurchase()")
        Log.d("YOLO", "purchase: ${purchase}")
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {

            Log.d("YOLO", "[${Thread.currentThread().name}] purchase.purchaseState == Purchase.PurchaseState.PURCHASED")

            val consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()

            // 소비성 제품은 소비를 해주어야 결제가 완료되며, 재구매할 수 있습니다.
            billingClient.consumeAsync(consumeParams) { billingResult, purchaseToken ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // 소비 성공
                    Log.d("YOLO", "[${Thread.currentThread().name}] handlePurchase() succeed.")
                    callback.onSuccess(purchase)
                } else {
                    // 소비 실패
                    Log.d("YOLO", "[${Thread.currentThread().name}] handlePurchase() fail. responseCode: ${billingResult.responseCode}")
                    callback.onFailure(billingResult.responseCode)
                }
            }

        } else {
            Log.d("YOLO", "[${Thread.currentThread().name}] purchase.purchaseState != Purchase.PurchaseState.PURCHASED")
            // 3일 후 자동으로 취소
            return
        }
    }

//    fun checkIfPurchasedButNotConsumed(type: String = BillingClient.SkuType.INAPP) {
//
//        Log.d("YOLO", "[${Thread.currentThread().name}] checkIfPurchasedButNotConsumed()")
//        if (billingClient.isReady) {
//            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient.isReady")
//            billingClient.queryPurchasesAsync(type) { _: BillingResult, purchases: List<Purchase> ->
//                if (purchases.isNotEmpty()) {
//                    Log.d("YOLO", "[${Thread.currentThread().name}] purchases is not empty")
//                    for (purchase in purchases) {
//                        handlePurchase(purchase)
//                    }
//                } else {
//                    Log.d("YOLO", "[${Thread.currentThread().name}] purchases is empty")
//                }
//            }
//        } else {
//            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient.isNotReady")
//        }
//    }

    fun queryPurchases(type: String = BillingClient.SkuType.INAPP) {
        Log.d("YOLO", "[${Thread.currentThread().name}] queryPurchases()")
        if (billingClient.isReady) {
            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient is ready.")
            billingClient.queryPurchasesAsync(type) { _: BillingResult, purchases: List<Purchase> ->
                Log.d("YOLO", "[${Thread.currentThread().name}] purchases.size: ${purchases.size}")
                for (purchase in purchases) {
                    Log.d("YOLO", "[${Thread.currentThread().name}] purchase: ${purchase}")
                }
            }
        } else {
            Log.d("YOLO", "[${Thread.currentThread().name}] billingClient is not ready.")
            return
        }
    }

    fun endConnection() {
        billingClient.endConnection()
    }
}
