package com.rightdirection.megapet.ui.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MemberRepository
) : ViewModel() {

    val loginResponse: MutableLiveData<Response<Member>> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun login(loginDetail: Member){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
            withContext(Dispatchers.Main) {
                val response = repository.login(loginDetail)
                loginResponse.value = response
            }
        }
    }

    fun saveAuthToken(jwt:String,email:String,password:String){
        viewModelScope.launch {
            repository.saveAuthToken(jwt,email,password)
        }
    }

}