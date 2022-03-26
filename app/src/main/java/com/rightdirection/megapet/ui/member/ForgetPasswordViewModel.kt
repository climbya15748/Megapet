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
class ForgetPasswordViewModel  @Inject constructor(
    private val repository: MemberRepository
) : ViewModel() {

    val forgetPasswordResponse: MutableLiveData<Response<Member>> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun forgetPassword(forgetPasswordDetail: Member){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
            withContext(Dispatchers.Main) {
                val response = repository.forgetPassword(forgetPasswordDetail)
                forgetPasswordResponse.value = response
            }
        }
    }
}