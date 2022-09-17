package com.yologger.inapp_payment_nonconsumable_product

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
    private val buttonRemoveAdvertisement: Button by lazy { findViewById<Button>(R.id.activity_main_button_remove_ad) }

    private lateinit var billingManager: BillingManager

    private var skuId = "non_consumable_product_id"

    private var mSkuDetailsList = listOf<SkuDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupBilling()
    }

    override fun onResume() {
        super.onResume()
        Log.d("YOLO", "[${Thread.currentThread().name}] onResume()")
        billingManager.checkIfPurchasedButNotAcknowledged()
    }

    private fun setupUI() {
        buttonRemoveAdvertisement.setOnClickListener {
            purchase()
        }
    }

    private fun setupBilling() {
        billingManager = BillingManager(this, object : BillingManager.Callback {

            override fun onBillingManagerReady() {

                runOnUiThread {
                    enableUpgradeButton()
                }

                // 구매 가능한 제품 목록 조회
                billingManager.querySkuDetails(BillingClient.SkuType.INAPP, skuId) { skuDetailsList: List<SkuDetails> ->
                    runOnUiThread {
                        // 조회한 제품 목록 저장
                        mSkuDetailsList = skuDetailsList
                        Log.d("YOLO", "[${Thread.currentThread().name}] mSkuDetailsList: ${mSkuDetailsList.toString()}")
                    }
                }

                // 이미 구매했는지 확인
                billingManager.checkIfAlreadyPurchased(skuId) { isAlreadyPurchased ->
                    Log.d("YOLO", "[${Thread.currentThread().name}] isAlreadyPurchased: ${isAlreadyPurchased}")
                    if (isAlreadyPurchased) {
                        runOnUiThread {
                            removeAdvertisement()
                        }
                    } else {
                        runOnUiThread {
                            showAdvertisement()
                        }
                    }
                }
            }

            override fun onSuccess(purchase: Purchase) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Successfully purchased.", Toast.LENGTH_SHORT).show()
                    removeAdvertisement()
                }
            }

            override fun onFailure(errorCode: Int) {
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
        Log.d("YOLO", "[${Thread.currentThread().name}] purchase()")
        for (skuDetail in mSkuDetailsList) {
            if (skuDetail.sku == skuId) {
                billingManager.purchase(skuDetail)
            } else {
                Toast.makeText(this@MainActivity, "Cannot find item.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun enableUpgradeButton () {
        buttonRemoveAdvertisement.isEnabled = true
    }

    private fun removeAdvertisement() {
        viewAdvertisement.visibility = View.GONE
        buttonRemoveAdvertisement.visibility = View.GONE
        buttonRemoveAdvertisement.isEnabled = false
    }

    private fun showAdvertisement() {
        viewAdvertisement.visibility = View.VISIBLE
        buttonRemoveAdvertisement.visibility = View.VISIBLE
        buttonRemoveAdvertisement.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        billingManager.endConnection()
    }
}