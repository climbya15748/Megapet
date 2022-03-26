package com.rightdirection.megapet.model.member

import com.google.gson.annotations.SerializedName


data class PointHistory(
    @SerializedName("id") val id:String?=null,
    @SerializedName("member_id") val member_id:String?=null,
    @SerializedName("inv_no") val inv_no:String?=null,
    @SerializedName("amount") val amount:Double?=null,
    @SerializedName("expire_on") val expire_on:String?=null
)
