package com.rightdirection.megapet.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id:String,
    @SerializedName("firstname") val firstname:String,
)
