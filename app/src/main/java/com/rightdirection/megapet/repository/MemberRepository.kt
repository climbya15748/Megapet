package com.rightdirection.megapet.repository

import com.rightdirection.megapet.model.Member
import retrofit2.Response

interface MemberRepository {

    suspend fun getMemberInfo(jwt:String,id:String): Member

}