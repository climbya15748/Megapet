package com.rightdirection.megapet.api

import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.model.MemberDto
import retrofit2.Response
import retrofit2.http.*

interface RdApi {

//    @GET("member/read?id=934407292")
//    suspend fun getPost(): Response<Member>

    @GET("member/read")
    suspend fun getPost2(
        @Query("id") Id:String
    ): Response<MemberDto>

    @GET("member/read")
    suspend fun getMemberInfo(
        @Header("Authorization") jwt:String,
        @Query("id") id:String
    ): MemberDto


    @POST("member/create")
    suspend fun postRegister(
        @Body registrationDetail: Member
    ): Response<Member>

}