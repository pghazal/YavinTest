package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Data(
    @SerializedName("transactions")
    val transactions: List<Transaction>?
) : Parcelable