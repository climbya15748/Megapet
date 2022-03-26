package com.rightdirection.megapet.di

import com.rightdirection.megapet.api.RdApi
import com.rightdirection.megapet.preferences.PreferenceManager
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
    fun provideMemberRepository(api:RdApi, preference: PreferenceManager): MemberRepository{
        return MemberRepositoryImpl(api,preference)
    }
}