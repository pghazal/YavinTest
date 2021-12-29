package com.pghaz.yavintest.model

import com.google.gson.annotations.SerializedName

enum class Status(private val value: String) {
    @SerializedName("ok")
    OK("ok"),

    @SerializedName("ko")
    KO("ko");

    companion object {
        fun fromString(value: String?): Status {
            for (item in values()) {
                if (item.value.equals(value, true)) {
                    return item
                }
            }

            throw IllegalArgumentException("No constant with value $value found")
        }
    }
}