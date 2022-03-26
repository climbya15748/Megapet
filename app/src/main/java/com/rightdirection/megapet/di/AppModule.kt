package com.rightdirection.megapet.di


import android.content.Context
import com.rightdirection.megapet.MainActivity
import com.rightdirection.megapet.preferences.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainActivity{
        return app as MainActivity
    }

    @Singleton
    @Provides
    fun provideRandomString():String{
        return "Ramdom TEXT : ksnfgdojkaxdfnmklsf"
    }


    @Provides
    @Singleton
    fun provideLoginPreference(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }


}