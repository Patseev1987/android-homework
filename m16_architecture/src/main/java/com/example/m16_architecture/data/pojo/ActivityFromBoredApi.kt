package com.example.m16_architecture.data.pojo


import com.google.gson.annotations.SerializedName

data class ActivityFromBoredApi(
    @SerializedName("accessibility")
    val accessibility: Double,
    @SerializedName("activity")
    val activity: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String
)