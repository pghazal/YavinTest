package com.pghaz.yavintest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pghaz.yavintest.model.yavin.PaymentResponse
import com.pghaz.yavintest.model.yavin.Status
import com.pghaz.yavintest.utils.Constants

@Entity(tableName = Constants.RECEIPTS_TABLE)
data class Receipt(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "transactionId")
    var transactionId: String,

    @ColumnInfo(name = "amount")
    var amount: String? = null,

    @ColumnInfo(name = "cardToken")
    var cardToken: String? = null,

    @ColumnInfo(name = "clientTicket")
    var clientTicket: String? = null,

    @ColumnInfo(name = "giftAmount")
    var giftAmount: String? = null,

    @ColumnInfo(name = "reference")
    var reference: String? = null,

    @ColumnInfo(name = "signatureRequired")
    var signatureRequired: String? = null,

    @ColumnInfo(name = "status")
    var status: Status? = null,

    @ColumnInfo(name = "totalAmount")
    var totalAmount: String? = null
)

fun PaymentResponse.toReceipt(): Receipt {
    return Receipt(transactionId!!, amount, cardToken, clientTicket, giftAmount, reference, signatureRequired, status, totalAmount)
}