package com.rightdirection.megapet.ui.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rightdirection.megapet.model.member.PointHistory
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
class MemberPointHistoryViewModel @Inject constructor(
    private val repository: MemberRepository,
    private val preference: PreferenceManager
) : ViewModel() {


    private val _isLogin : MutableLiveData<Boolean> = MutableLiveData()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _pointHistoryResponse: MutableLiveData<Response<List<PointHistory>>> = MutableLiveData()
    val pointHistoryResponse: LiveData<Response<List<PointHistory>>> get() = _pointHistoryResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    init {
        viewModelScope.launch {
            //check is login or not
            preference.jwtFlow.collect { jwt: String ->
                _isLogin.apply {
                    value = jwt.isNotEmpty()
                }
            }
        }
    }

    fun getPointHistory(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            withContext(Dispatchers.Main) {
                preference.jwtFlow.collect { value: String ->
                    val result = repository.getPointHistory(value)
                    _pointHistoryResponse.value = result
                }
            }
        }
    }


}