package com.rightdirection.megapet.repository

import com.rightdirection.megapet.api.RetrofitInstance
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.model.MemberDto
import retrofit2.Response

class Repository {

//    suspend fun getPost():Response<Member>{
//        return RetrofitInstance.api.getPost()
//    }

    suspend fun getPost2(Id:String):Response<MemberDto>{
        return RetrofitInstance.api.getPost2(Id)
    }

    suspend fun postRegister(registrationDetail: Member): Response<Member>{
        return RetrofitInstance.api.postRegister(registrationDetail)
    }

}