package com.rightdirection.megapet.ui.member


import androidx.lifecycle.*
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.model.member.ObjEditPassword
import com.rightdirection.megapet.model.member.ObjOtp
import com.rightdirection.megapet.model.member.ObjQRString
import com.rightdirection.megapet.preferences.PreferenceManager
import com.rightdirection.megapet.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val repository: MemberRepository,
    private val preference: PreferenceManager
    ): ViewModel() {

    private val _memberResponse: MutableLiveData<Response<Member>> = MutableLiveData()
    val memberResponse: LiveData<Response<Member>> get() = _memberResponse

    val updateInfoResponse:MutableLiveData<Response<Member>> = MutableLiveData()
    val updatePasswordResponse:MutableLiveData<Response<Member>> = MutableLiveData()

    val qrStringResponse:MutableLiveData<Response<ObjQRString>> = MutableLiveData()

    val sendOtpRequestResponse:MutableLiveData<Response<Member>> = MutableLiveData()
    val sendOtpVerificationResponse:MutableLiveData<Response<Member>> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    init{
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.getMemberInfo(value)
                    _memberResponse.value = result
                }
            }
        }
    }

    fun postUpdateMemberInfo(memberInfo: Member){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.postUpdateInfo(value, memberInfo)
                    updateInfoResponse.value = result
                }
            }
        }
    }

    fun postUpdatePassword(editPasswordRequest:ObjEditPassword){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.postUpdatePassword(value, editPasswordRequest)
                    updatePasswordResponse.value = result
                }
            }
        }
    }


    fun getQRString(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.getQRString(value)
                    qrStringResponse.value = result
                }
            }
        }
    }

    fun postOtpRequest(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.postOtpRequest(value)
                    sendOtpRequestResponse.value = result
                }
            }
        }
    }

    fun postOtpVerification(otp:ObjOtp){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.postOtpVerification(value,otp)
                    sendOtpVerificationResponse.value = result
                }
            }
        }
    }














}