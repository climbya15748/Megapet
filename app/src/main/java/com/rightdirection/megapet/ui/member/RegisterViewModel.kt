package com.rightdirection.megapet.ui.member

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.model.member.ObjPhone
import com.rightdirection.megapet.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: MemberRepository
) : ViewModel() {


    val registrationResponse :  MutableLiveData<Response<Member>> = MutableLiveData()
    val regOtpResponse : MutableLiveData<Response<Member>> = MutableLiveData()



    fun postRegister(registrationDetail: Member){
        viewModelScope.launch {
            Log.d("postRegister:",registrationDetail.toString())
            val response = repository.postRegistration(registrationDetail)
            registrationResponse.value = response
            Log.d("response:",response?.body().toString())
        }
    }

    fun postRegOtpRequest(phone: ObjPhone){
        viewModelScope.launch {
            val response = repository.postRegOtpRequest(phone)
            regOtpResponse.value = response
            Log.d("response:",response?.body().toString())
        }
    }



}