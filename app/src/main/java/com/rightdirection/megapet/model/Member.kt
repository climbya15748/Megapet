package com.rightdirection.megapet.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@SerializedName("")

@Parcelize
data class Member(
    val id:String?,
    val firstname:String?,
    val lastname:String?,
    val password:String?,
    val email:String?,
    val point_bal:Double?,
    val point_earned:Double?,
    val point_redeemed:Double?,
    val phone:String?,
    val type:String?,
    val join_date:String?=null,
    val order_history:List<String>? = listOf()

    ): Parcelable
