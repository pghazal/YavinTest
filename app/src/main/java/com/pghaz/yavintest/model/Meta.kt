package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Meta(
    @SerializedName("code")
    var code: Int? = null,
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("start")
    var start: Int? = null,
    @SerializedName("total")
    var total: Int? = null
) : Parcelable