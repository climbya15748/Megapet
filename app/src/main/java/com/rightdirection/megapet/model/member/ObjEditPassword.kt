package com.rightdirection.megapet.model.member

import com.google.gson.annotations.SerializedName

data class ObjEditPassword(
    @SerializedName("current_password") val currentPassword:String,
    @SerializedName("new_password") val newPassword:String
)
