package com.rightdirection.megapet.repository

import com.rightdirection.megapet.model.member.*
import retrofit2.Response

interface MemberRepository {

    suspend fun getMemberInfo(jwt:String): Response<Member>

    suspend fun postRegistration(registrationDetail:Member): Response<Member>

    suspend fun login(loginDetail:Member):Response<Member>

    suspend fun postUpdateInfo(jwt:String,memberInfo:Member):Response<Member>

    suspend fun postUpdatePassword(jwt:String,editPasswordRequest: ObjEditPassword) : Response<Member>

    suspend fun getQRString(jwt:String):Response<ObjQRString>

    suspend fun getPointHistory(jwt:String):Response<List<PointHistory>>

    suspend fun saveAuthToken(jwt:String,email:String,password:String)

    suspend fun clearAuthToken()

    suspend fun forgetPassword(forgetPasswordDetail:Member):Response<Member>

    suspend fun saveLocalePreference(locale:String)

    suspend fun postOtpRequest(jwt: String):Response<Member>

    suspend fun postOtpVerification(jwt:String, otp:ObjOtp ):Response<Member>

}