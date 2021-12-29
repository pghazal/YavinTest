package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PaymentResponse(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("cardToken")
    val cardToken: String?,
    @SerializedName("clientTicket")
    val clientTicket: String?,
    @SerializedName("giftAmount")
    val giftAmount: String?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("signatureRequired")
    val signatureRequired: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalAmount")
    val totalAmount: String?,
    @SerializedName("transactionId")
    val transactionId: String?,
) : Parcelable