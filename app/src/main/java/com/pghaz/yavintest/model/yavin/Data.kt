package com.pghaz.yavintest.model.yavin


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Data(
    @SerializedName("transactions")
    var transactions: List<Transaction>? = null
) : Parcelable