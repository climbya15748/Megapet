package com.rightdirection.megapet.ui.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberPaymentScanViewModel : ViewModel() {
    private val mCountDownHint = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val getCountDownHint: LiveData<String> = mCountDownHint
}