package com.rightdirection.megapet.ui.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rightdirection.megapet.repository.Repository

class MyAccountViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyAccountViewModel(repository) as T
    }
}