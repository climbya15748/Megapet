package com.rightdirection.megapet.ui.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.preferences.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberCardViewModel @Inject constructor(
    private val preference: PreferenceManager
) : ViewModel() {


    private val _isLogin : MutableLiveData<Boolean> = MutableLiveData()
    val isLogin: LiveData<Boolean> = _isLogin




    init {
        viewModelScope.launch {
            preference.jwtFlow.collect { jwt: String ->
                _isLogin.apply {
                    value = jwt.isNotEmpty()
                }
            }
        }

    }






}