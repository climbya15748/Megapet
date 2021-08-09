package com.rightdirection.megapet.ui.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val repository: Repository) : ViewModel() {


    val registrationResponse :  MutableLiveData<Response<Member>> = MutableLiveData()

    fun postRegister(registrationDetail: Member){
        viewModelScope.launch {
            val response = repository.postRegister(registrationDetail)
            registrationResponse.value = response
        }
    }




}