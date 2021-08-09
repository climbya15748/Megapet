package com.rightdirection.megapet.model

import com.google.gson.annotations.SerializedName


//@SerializedName("id") for API's name
data class MemberDto(
    @SerializedName("id") val id:String?,
    @SerializedName("firstname") val firstname:String?,
    @SerializedName("lastname") val lastname:String?,
    @SerializedName("password")  val password:String?,
    @SerializedName("email") val email:String?,
    @SerializedName("point_bal") val point_bal:Double?,
    @SerializedName("point_earned") val point_earned:Double?,
    @SerializedName("point_redeemed") val point_redeemed:Double?,
    @SerializedName("phone") val phone:String?,
    @SerializedName("type") val type:String?
)
