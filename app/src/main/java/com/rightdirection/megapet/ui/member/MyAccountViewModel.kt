package com.rightdirection.megapet.ui.member

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.model.MemberDto
import com.rightdirection.megapet.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response



class MyAccountViewModel (private val repository: Repository)  : ViewModel() {

    val myResponse: MutableLiveData<Response<Member>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<MemberDto>> = MutableLiveData()

//    fun getPost(){
//        viewModelScope.launch {
//            val response = repository.getPost()
//            myResponse.value = response
//        }
//    }

    fun getPost2(Id: String){
        viewModelScope.launch {
            val response = repository.getPost2(Id)
            myResponse2.value = response
        }
    }


}