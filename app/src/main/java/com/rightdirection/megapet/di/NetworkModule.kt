package com.rightdirection.megapet.di


import com.rightdirection.megapet.api.RdApi
import com.rightdirection.megapet.model.MemberDtoMapper
import com.rightdirection.megapet.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideMemberDtoMapper():MemberDtoMapper{
        return MemberDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRdApi(): RdApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RdApi::class.java)
    }

    @Singleton
    @Provides
    @Named("jwt")
    fun provideAuthToken(): String{
        return "Bearer EeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL3dwXC9hcGkiLCJhdWQiOiJBcHBsaWNhdGlvblVzZXIiLCJpYXQiOjE2Mjg1MDM1Mjl9.tGX3ylEI3fJ0ZuRFw6Uk5PuDz9wghpfh3GKpZP8CP258NbYILIseaLvLEWvPHrU4OCClGsJbpZh94-IjHlyZkg"
    }

}