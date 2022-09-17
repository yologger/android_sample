package com.yologger.inapp_billing_subscription

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails

class MainActivity : AppCompatActivity() {

    private val viewAdvertisement: ConstraintLayout by lazy { findViewById<ConstraintLayout>(R.id.activity_main_constraintLayout_ad) }
    private val textViewVersion: TextView by lazy { findViewById<TextView>(R.id.activity_main_textView_version) }
    private val buttonSubscribe: Button by lazy { findViewById<Button>(R.id.activity_main_button_subscribe) }

    private lateinit var billingManager: BillingManager

    private var skuId = "subscription_id"

    private var mSkuDetailsList = listOf<SkuDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupBilling()
    }

    override fun onResume() {
        super.onResume()
        billingManager.checkIfPurchasedButNotAcknowledged()
    }

    private fun setupUI() {
        buttonSubscribe.setOnClickListener { purchase() }
    }

    private fun setupBilling() {
        billingManager = BillingManager(this, object: BillingManager.Callback {

            override fun onBillingManagerReady() {
                Log.d("YOLO", "${Thread.currentThread().name}] onBillingManagerReady()")
                runOnUiThread {
                    enableButtonSubscribe()
                }

                // 구매 가능한 제품 조회
                billingManager.querySkuDetails(BillingClient.SkuType.SUBS, skuId) { skuDetailsList ->
                    runOnUiThread {
                        mSkuDetailsList = skuDetailsList
                        Log.d("YOLO", "${Thread.currentThread().name}] mSkuDetailsList: ${mSkuDetailsList}")
                    }
                }

               // 이미 구매했는지 확인
                billingManager.checkIfAlreadySubscribing(skuId) { isSubscribing ->
                    Log.d("YOLO", "${Thread.currentThread().name}] isSubscribing: ${isSubscribing}")
                    if (isSubscribing) {
                        runOnUiThread {
                            handleWhenSubscribing()
                        }
                    } else {
                        runOnUiThread {
                            handleWhenUnsubscribing()
                        }
                    }
                }
            }

            override fun onSuccess(purchase: Purchase) {
                Log.d("YOLO", "${Thread.currentThread().name}] onSuccess()")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Successfully purchased.", Toast.LENGTH_SHORT).show()
                    handleWhenSubscribing()
                }
            }

            override fun onFailure(errorCode: Int) {
                Log.d("YOLO", "${Thread.currentThread().name}] onFailure()")
                when (errorCode) {
                    BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Already purchased.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    BillingClient.BillingResponseCode.USER_CANCELED -> {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Purchase cancelled.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                        /*
                            이미 purchase.isAcknowledge == true 거나
                            구글 플레이 콘솔에서 acknowledgePurchase(purchase)를 처리 중일 때
                            다시 acknowledgePurchase()를 호출했을 때
                         */
                        Log.d("YOLO", "${Thread.currentThread().name}] BillingClient.BillingResponseCode.DEVELOPER_ERROR")
                    }
                    else -> {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Error: $errorCode", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })
    }

    private fun purchase() {
        Log.d("YOLO", "${Thread.currentThread().name}] purchase()")
        for (skuDetail in mSkuDetailsList) {
            if (skuDetail.sku == skuId) {
                billingManager.purchase(skuDetail)
            } else {
                Toast.makeText(this@MainActivity, "Cannot find item.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun enableButtonSubscribe() {
        buttonSubscribe.isEnabled = true
    }

    private fun handleWhenSubscribing() {
        Log.d("YOLO", "${Thread.currentThread().name}] handleWhenSubscribing()")
        viewAdvertisement.visibility = View.GONE
        textViewVersion.text = "Premium"
        textViewVersion.setTextColor(Color.RED)
        buttonSubscribe.isEnabled = false
        buttonSubscribe.visibility = View.GONE
    }

    private fun handleWhenUnsubscribing() {
        Log.d("YOLO", "${Thread.currentThread().name}] handleWhenUnsubscribing()")
        viewAdvertisement.visibility = View.VISIBLE
        textViewVersion.text = "Free"
        textViewVersion.setTextColor(Color.BLACK)
        buttonSubscribe.isEnabled = true
        buttonSubscribe.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        billingManager.endConnection()
    }
}