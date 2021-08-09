package com.rightdirection.megapet.ui.member

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.rightdirection.megapet.databinding.FragmentRegisterBinding
import com.rightdirection.megapet.model.Member
import com.rightdirection.megapet.repository.Repository


class RegisterFragment : Fragment() {



    private lateinit var mRegisterViewModel: RegisterViewModel
    private var _binding : FragmentRegisterBinding ?= null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mRegisterViewModel = ViewModelProvider(this,ViewModelFactory<RegisterViewModel>(Repository())).get(RegisterViewModel::class.java)
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        val form = Member(null,"jijj","curriy","123","sdfjiosfjd@mgia.com",null,null,null,null,"")


        binding.registerSubmitBtn.setOnClickListener {
            Log.d("onClick:","registerSubmitBtn")
            mRegisterViewModel.postRegister(form)
        }


        mRegisterViewModel.registrationResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                Log.d("rs:",response?.body().toString())
                Log.d("rs:",response?.code().toString())
                Log.d("rs:",response?.message().toString())
            }else{
                Log.d("error body:",response.errorBody().toString())
                Log.d("error code:", response.code().toString())
            }
        }
        )



        return binding.root
    }



}