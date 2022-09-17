package com.yologger.inapp_pay_consumable_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val buttonPurchaseItem: Button by lazy { findViewById<Button>(R.id.activity_main_button_purchase_item) }
    private val buttonUseItem: Button by lazy { findViewById<Button>(R.id.activity_main_button_use_item) }
    private val textViewItemQuantity: TextView by lazy { findViewById<TextView>(R.id.activity_main_textView_item_quantity) }

    private var skuId = "consumable_product_id"
    private var itemQuantity = 0

    private lateinit var billingManager: BillingManager

    private var mSkuDetailsList = listOf<SkuDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupBilling()
    }

    private fun setupBilling() {
        billingManager = BillingManager(this, object : BillingManager.Callback {

            override fun onBillingManagerReady() {

                runOnUiThread {
                    enableButtonPurchase()
                }

                // 구매 가능한 제품 목록 조회
                billingManager.querySkuDetails(BillingClient.SkuType.INAPP, skuId) { skuDetailsList: List<SkuDetails> ->
                    runOnUiThread {
                        // 조회한 제품 목록 저장
                        mSkuDetailsList = skuDetailsList
                    }
                }
            }

            override fun onSuccess(purchase: Purchase) {
                Log.d("YOLO", "onSuccess()")
                runOnUiThread {
                    handleOnItemPurchased()
                    Toast.makeText(this@MainActivity, "Successfully purchased.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(errorCode: Int) {
                Log.d("YOLO", "onFailure()")
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
                    else -> {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Error: $errorCode", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    private fun setupUI() {

        textViewItemQuantity.text = "$itemQuantity"

        buttonPurchaseItem.setOnClickListener {
            purchase()
        }

        buttonUseItem.setOnClickListener {
            handleOnItemUsed()
        }
    }

    private fun purchase() {
        for (skuDetail in mSkuDetailsList) {
            if (skuDetail.sku == skuId) {
                billingManager.purchase(skuDetail)
            } else {
                Toast.makeText(this@MainActivity, "Cannot find Product.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun enableButtonPurchase() {
        buttonPurchaseItem.isEnabled = true
    }

    private fun handleOnItemPurchased() {
        buttonUseItem.isEnabled = true
        itemQuantity += 1
        textViewItemQuantity.text = "$itemQuantity"
    }

    private fun handleOnItemUsed() {
        itemQuantity -= 1
        textViewItemQuantity.text = "$itemQuantity"

        if (itemQuantity == 0) {
            buttonUseItem.isEnabled = false
        }

        Toast.makeText(this@MainActivity, "Used.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        billingManager.endConnection()
    }
}