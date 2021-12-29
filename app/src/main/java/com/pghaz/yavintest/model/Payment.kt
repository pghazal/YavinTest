package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Payment(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("cardToken")
    val cardToken: String?,
    @SerializedName("cartId")
    val cartId: String?,
    @SerializedName("client")
    val client: String?,
    @SerializedName("clientTicket")
    val clientTicket: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("giftAmount")
    val giftAmount: String?,
    @SerializedName("medium")
    val medium: String?,
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
    @SerializedName("transactionType")
    val transactionType: String?
) : Parcelable