package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Transaction(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("logical_number")
    val logicalNumber: String?,
    @SerializedName("pan")
    val pan: String?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("transaction_id")
    val transactionId: String?
) : Parcelable