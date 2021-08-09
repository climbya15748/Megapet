package com.rightdirection.megapet.repository

import com.rightdirection.megapet.api.RdApi
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.model.MemberDtoMapper
import retrofit2.Response


class MemberRepositoryImpl(
    private val api:RdApi,
    private val mapper: MemberDtoMapper
    ):MemberRepository {

    override suspend fun getMemberInfo(jwt:String,id: String): Member {
        return mapper.mapToDomainModel(api.getMemberInfo(jwt,id))
    }


}