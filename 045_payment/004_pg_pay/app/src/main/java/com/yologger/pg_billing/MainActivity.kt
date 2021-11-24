package com.yologger.pg_billing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.data.sdk.IamPortResponse
import com.iamport.sdk.data.sdk.PG
import com.iamport.sdk.data.sdk.PayMethod
import com.iamport.sdk.domain.core.Iamport
import java.util.*

class MainActivity : AppCompatActivity() {

    private val buttonBuy: Button by lazy { findViewById<Button>(R.id.activity_main_button_buy)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBilling()
    }

    override fun onStart() {
        super.onStart()
        setupUI()
    }

    private fun setupBilling() {
        Iamport.init(this)
    }

    private fun setupUI() {
        buttonBuy.setOnClickListener {
            pay()
        }
    }

    private fun pay() {

        // 아임포트, 부트페이

        // 아임포트 지원 PG사(결제대행사)
        // https://guide.iamport.kr/17365631-9d96-45dd-b001-dc8d2f179ef3

        // 아임포트 수수료 확인
        // https://guide.iamport.kr/17365631-9d96-45dd-b001-dc8d2f179ef3

        val userCode = "iamport"    // 가맹점 코드, "iamport"는 테스트용, 아임포트 홈페이지에서 발급받은 가맹점 코드를 입력합니다.

        val request = IamPortRequest(
            pg = PG.naverpay.makePgRawName(""),     // PG사
            pay_method = PayMethod.card,                    // 신용카드
            name = "Test Order",                       // 주문명
            merchant_uid = "sample_aos_${Date().time}",      // 주문번호
            amount = "1000",     // 결제 금액
            buyer_name = "yologger"     // 주문자 이
        )

        // PG.html5_inicis.makePgRawName("")
        // PG.kakaopay
        PG.naverpay.makePgRawName("")
        PG.danal_tpay.makePgRawName("")

        Iamport.payment(userCode = userCode, iamPortRequest = request, paymentResultCallback = { response: IamPortResponse? ->
            // 결제 완료 시.
            Log.d("YOLO", response.toString())
            
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Iamport.close()
    }
}

