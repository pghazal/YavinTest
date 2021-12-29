package com.pghaz.yavintest.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class YavinResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("meta")
    val meta: Meta?
) : Parcelable