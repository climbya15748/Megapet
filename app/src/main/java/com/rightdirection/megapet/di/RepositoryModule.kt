package com.rightdirection.megapet.di

import com.rightdirection.megapet.api.RdApi
import com.rightdirection.megapet.model.MemberDto
import com.rightdirection.megapet.model.MemberDtoMapper
import com.rightdirection.megapet.repository.MemberRepository
import com.rightdirection.megapet.repository.MemberRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMemberRepository(
        api:RdApi,
        memberDtoMapper: MemberDtoMapper
    ): MemberRepository{
        return MemberRepositoryImpl(
            api,memberDtoMapper
        )
    }
}