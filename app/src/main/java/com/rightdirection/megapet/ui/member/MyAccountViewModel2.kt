package com.rightdirection.megapet.ui.member


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MyAccountViewModel2 @Inject constructor(
    private val repository: MemberRepository,
    @Named("jwt") private val jwt: String
    ): ViewModel() {

    private val _memberResponse: MutableLiveData<Member> = MutableLiveData()
    val memberResponse: LiveData<Member> get() = _memberResponse

    init {
        viewModelScope.launch {
            val result = repository.getMemberInfo(jwt,"934407292")
            _memberResponse.value = result
        }
    }




}