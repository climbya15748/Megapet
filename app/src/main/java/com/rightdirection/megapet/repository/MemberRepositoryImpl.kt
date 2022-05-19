package com.rightdirection.megapet.repository

import com.rightdirection.megapet.api.RdApi
import com.rightdirection.megapet.model.member.*
import com.rightdirection.megapet.preferences.PreferenceManager
import retrofit2.Response


class MemberRepositoryImpl(
    private val api:RdApi,
    private val preference: PreferenceManager
    ):MemberRepository {

    override suspend fun getMemberInfo(jwt: String): Response<Member> {
        return api.getMemberInfo(jwt)
    }

    override suspend fun postRegistration(registrationDetail: Member): Response<Member> {
        return api.postRegister(registrationDetail)
    }

    override suspend fun login(loginDetail: Member): Response<Member> {
        return api.login(loginDetail)
    }

    override suspend fun postUpdateInfo(jwt: String,memberInfo: Member): Response<Member> {
        return api.postUpdateMemberInfo(jwt,memberInfo)
    }

    override suspend fun postUpdatePassword(jwt: String, editPasswordRequest: ObjEditPassword): Response<Member> {
        return api.postUpdatePassword(jwt,editPasswordRequest)
    }

    override suspend fun getQRString(jwt: String): Response<ObjQRString> {
        return api.getQRString(jwt)
    }

    override suspend fun getPointHistory(jwt: String): Response<List<PointHistory>> {
        return api.getPointHistory(jwt)
    }

    override suspend fun forgetPassword(forgetPasswordDetail: Member): Response<Member> {
        return api.forgetPassword(forgetPasswordDetail)
    }

    override suspend fun saveAuthToken(jwt: String,email:String,password:String) {
        preference.saveAuthToken(jwt,email,password)
    }

    override suspend fun clearAuthToken() {
        preference.clearAuthToken()
    }

    override suspend fun saveLocalePreference(locale:String){
        preference.saveLocaleSetting(locale)
    }

    override suspend fun postOtpRequest(jwt: String): Response<Member> {
        return api.postOtpRequest(jwt)
    }

    override suspend fun postOtpVerification(jwt: String, otp: ObjOtp): Response<Member> {
        return api.postOtpVerification(jwt,otp)
    }

    override suspend fun postRegOtpRequest(phone: ObjPhone): Response<Member> {
        return api.postRegOtpReqeust(phone)
    }


}