package com.pghaz.yavintest.model.yavin

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("id")
    var id: Int,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("title")
    var title: String,
)
