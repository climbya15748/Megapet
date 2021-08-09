package com.rightdirection.megapet.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val mCountDownHint = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val getCountDownHint: LiveData<String> = mCountDownHint




}