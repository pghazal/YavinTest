package com.pghaz.yavintest.model

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("amount")
    var amount: String? = null,
    @SerializedName("title")
    var title: String? = null,
)
