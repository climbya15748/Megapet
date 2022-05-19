package com.rightdirection.megapet.model.member
import com.google.gson.annotations.SerializedName

data class ObjOtp(
    @SerializedName("otp") val otp:String
)

data class ObjPhone(
    @SerializedName("phone") val phone:String
)