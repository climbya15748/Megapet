package com.rightdirection.megapet.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.preferences.PreferenceManager
import com.rightdirection.megapet.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: MemberRepository,
    preference: PreferenceManager
) : ViewModel() {

    private val _isLogin : MutableLiveData<Boolean> = MutableLiveData()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _currentLocale : MutableLiveData<String> = MutableLiveData()
    val currentLocale: LiveData<String> = _currentLocale

    init {
        viewModelScope.launch {
            preference.jwtFlow.collect { jwt: String ->
                _isLogin.apply {
                    value = jwt.isNotEmpty()
                }
            }

        }


    }

    fun logout(){
        viewModelScope.launch {
            repository.clearAuthToken()
        }
    }


    fun saveLocalePreference(locale:String){
        viewModelScope.launch {
            repository.saveLocalePreference(locale)
        }
    }

}