package com.pghaz.yavintest.model.yavin


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class YavinResponse(
    @SerializedName("data")
    var `data`: Data? = null,
    @SerializedName("meta")
    var meta: Meta? = null
) : Parcelable