package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Meta(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("total")
    val total: Int?
) : Parcelable