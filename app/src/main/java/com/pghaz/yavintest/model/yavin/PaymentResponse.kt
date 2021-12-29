package com.pghaz.yavintest.model.yavin


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PaymentResponse(
    @SerializedName("amount")
    var amount: String? = null,
    @SerializedName("cardToken")
    var cardToken: String? = null,
    @SerializedName("clientTicket")
    var clientTicket: String? = null,
    @SerializedName("giftAmount")
    var giftAmount: String? = null,
    @SerializedName("reference")
    var reference: String? = null,
    @SerializedName("signatureRequired")
    var signatureRequired: String? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("totalAmount")
    var totalAmount: String? = null,
    @SerializedName("transactionId")
    var transactionId: String? = null,
) : Parcelable