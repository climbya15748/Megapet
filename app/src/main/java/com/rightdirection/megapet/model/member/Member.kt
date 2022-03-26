package com.rightdirection.megapet.model.member

import com.google.gson.annotations.SerializedName


//@SerializedName("id") for API's name
data class Member(
    @SerializedName("id") val id:String?=null,
    @SerializedName("firstname") val firstname:String?="",
    @SerializedName("lastname") val lastname:String?="",
    @SerializedName("password")  val password:String?=null,
    @SerializedName("email") val email:String?=null,
    @SerializedName("point_bal") val point_bal:Double?=0.0,
    @SerializedName("point_earned") val point_earned:Double?=0.0,
    @SerializedName("point_redeemed") val point_redeemed:Double?=0.0,
    @SerializedName("phone") val phone:String?=null,
    @SerializedName("type") val type:String?="",
    @SerializedName("login_token") val login_token:String?=null,
    @SerializedName("sign_up_by") val sign_up_by:String?=null,
    @SerializedName("otp") val otp:String?=null,
    @SerializedName("is_phone_valid") val is_phone_valid:String?=null
)
