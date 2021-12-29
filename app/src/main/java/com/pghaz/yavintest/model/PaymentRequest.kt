package com.pghaz.yavintest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentRequest(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("cartId")
    val cartId: String?,
    @SerializedName("client")
    val client: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("forcePayment")
    val forcePayment: Boolean?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("receiptTicket")
    val receiptTicket: List<String>?,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("showPrepay")
    val showPrepay: Boolean?,
    @SerializedName("showPostpay")
    val showPostpay: Boolean?,
    @SerializedName("showOnlyLastPostpay")
    val showOnlyLastPostpay: Boolean?,
    @SerializedName("transactionType")
    val transactionType: String?,
    @SerializedName("vendorToken")
    val vendorToken: String?
) : Parcelable