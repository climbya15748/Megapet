package com.rightdirection.megapet.api

import com.rightdirection.megapet.model.member.*
import retrofit2.Response
import retrofit2.http.*

interface RdApi {


    @GET("member/read")
    suspend fun getMemberInfo(
        @Header("Authorization") jwt:String
    ): Response<Member>

//    @GET("member/read")
//    suspend fun getMemberInfo(): Response<Member>


    @POST("member/create")
    suspend fun postRegister(
        @Body registrationDetail: Member
    ): Response<Member>

    @POST("member/update-info")
    suspend fun postUpdateMemberInfo(
        @Header("Authorization") jwt:String,
        @Body memberInfo: Member
    ): Response<Member>

    @POST("member/update-password")
    suspend fun postUpdatePassword(
        @Header("Authorization") jwt:String,
        @Body editPasswordRequest:ObjEditPassword
    ): Response<Member>


    @POST("member/login")
    suspend fun login(
        @Body loginDetail: Member
    ): Response<Member>

    @POST("member/get-QR")
    suspend fun getQRString(
        @Header("Authorization") jwt:String
    ): Response<ObjQRString>

    @POST("point-record/read")
    suspend fun getPointHistory(
        @Header("Authorization") jwt:String
    ): Response<List<PointHistory>>

    @POST("member/forget-password")
    suspend fun forgetPassword(
        @Body forgetPasswordDetail: Member
    ): Response<Member>

    @POST("member/send-otp")
    suspend fun postOtpRequest(
        @Header("Authorization") jwt:String
    ): Response<Member>

    @POST("member/verify-otp")
    suspend fun postOtpVerification(
        @Header("Authorization") jwt:String,
        @Body otp: ObjOtp
    ): Response<Member>




}