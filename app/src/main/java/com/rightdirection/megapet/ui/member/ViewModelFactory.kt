package com.rightdirection.megapet.ui.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rightdirection.megapet.repository.Repository

class ViewModelFactory<T1 : ViewModel?>(private val repository: Repository,private val listener: () -> T1? = { null }) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return listener() as T
    }


}

