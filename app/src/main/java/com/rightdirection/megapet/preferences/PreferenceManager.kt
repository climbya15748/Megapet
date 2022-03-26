package com.rightdirection.megapet.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")


@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext appContext: Context
) {

    private val settingsDataStore = appContext.dataStore

    companion object{
        private val LOGIN_JWT_KEY = stringPreferencesKey("jwt")
        private val LOGIN_EMAIL_KEY = stringPreferencesKey("email")
        private val LOGIN_PASSWORD_KEY = stringPreferencesKey("password")
        private val LOCALE_KEY = stringPreferencesKey("locale")
    }

    private lateinit var localeCode:String


    val jwtFlow: Flow<String> = appContext.dataStore.data.map { preferences->
        preferences[LOGIN_JWT_KEY] ?:""
    }

    val emailFlow: Flow<String> = appContext.dataStore.data.map { preferences->
        preferences[LOGIN_EMAIL_KEY] ?:""
    }

    val passwordFlow: Flow<String> = appContext.dataStore.data.map { preferences->
        preferences[LOGIN_PASSWORD_KEY] ?:""
    }

    val localeFlow: Flow<String> = appContext.dataStore.data.map{ preferences->
        preferences[LOCALE_KEY] ?:""
    }


    suspend fun saveAuthToken(jwt: String,email:String,password:String) {
        settingsDataStore.edit { settings ->
            settings[LOGIN_JWT_KEY] = jwt
            settings[LOGIN_EMAIL_KEY] = email
            settings[LOGIN_PASSWORD_KEY] = password
        }
    }

    suspend fun clearAuthToken(){
        settingsDataStore.edit { settings->
            settings.clear()
        }
    }

    suspend fun saveLocaleSetting(locale:String){
        settingsDataStore.edit { settings ->
            settings[LOCALE_KEY] = locale
        }
    }

    fun setLocaleCode(locale:String){
        localeCode = locale
    }

    fun getLocalePreference():String{
        return localeCode
    }







}

