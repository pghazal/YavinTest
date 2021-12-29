package com.pghaz.yavintest.model.yavin


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    @SerializedName("amount")
    var amount: Int? = null,
    @SerializedName("currency")
    var currency: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("logical_number")
    var logicalNumber: String? = null,
    @SerializedName("pan")
    var pan: String? = null,
    @SerializedName("scheme")
    var scheme: String? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("time")
    var time: String? = null,
    @SerializedName("transaction_id")
    var transactionId: String? = null
) : Parcelable