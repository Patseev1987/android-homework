package ru.bogdan.m14_retrofit.data.pojo


import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)