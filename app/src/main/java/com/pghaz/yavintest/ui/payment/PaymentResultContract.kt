package com.pghaz.yavintest.ui.payment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.pghaz.yavintest.model.yavin.PaymentRequest
import com.pghaz.yavintest.model.yavin.PaymentResponse
import com.pghaz.yavintest.model.yavin.Status
import com.pghaz.yavintest.utils.Constants
import org.json.JSONArray
import org.json.JSONException

class PaymentResultContract : ActivityResultContract<PaymentRequest, PaymentResponse>() {

    override fun createIntent(context: Context, payment: PaymentRequest): Intent {
        val intent = Intent()
        intent.component =
            ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
        intent.putExtra(Constants.KEY_VENDOR_TOKEN, payment.vendorToken)
        intent.putExtra(Constants.KEY_AMOUNT, payment.amount)
        intent.putExtra(Constants.KEY_CURRENCY, payment.currency)
        intent.putExtra(Constants.KEY_TRANSACTION_TYPE, payment.transactionType)
        intent.putExtra(Constants.KEY_REFERENCE, payment.reference)
        intent.putExtra(Constants.KEY_CLIENT, payment.client)
        val jArray = JSONArray(payment.receiptTicket)
        val receiptTicket = ArrayList<String>()
        for (i in 0 until jArray.length()) {
            try {
                receiptTicket.add(jArray.getString(i))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        intent.putExtra(Constants.KEY_RECEIPT_TICKET, receiptTicket)

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): PaymentResponse {
        return if (intent != null) {
            PaymentResponse(
                amount = intent.getStringExtra(Constants.KEY_AMOUNT),
                cardToken = intent.getStringExtra(Constants.KEY_CARD_TOKEN),
                clientTicket = intent.getStringExtra(Constants.KEY_CLIENT_TICKET),
                giftAmount = intent.getStringExtra(Constants.KEY_GIFT_AMOUNT),
                reference = intent.getStringExtra(Constants.KEY_REFERENCE),
                signatureRequired = intent.getStringExtra(Constants.KEY_SIGNATURE_REQUIRED),
                status = Status.fromString(intent.getStringExtra(Constants.KEY_STATUS)),
                totalAmount = intent.getStringExtra(Constants.KEY_TOTAL_AMOUNT),
                transactionId = intent.getStringExtra(Constants.KEY_TRANSACTION_ID)
            )
        } else {
            PaymentResponse(
                status = Status.KO
            )
        }
    }
}