package com.rightdirection.megapet.ui.member

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.MyAccountFragmentBinding
import com.rightdirection.megapet.repository.Repository
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyAccountFragment : Fragment() {

    val viewModel: MyAccountViewModel2 by viewModels()

    private var _binding : MyAccountFragmentBinding ?= null
    private val binding get() = _binding!!
    private lateinit var navController :NavController




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MyAccountFragmentBinding.inflate(inflater, container, false)

        viewModel.memberResponse.observe(viewLifecycleOwner,{memberResponse->
            Log.d("NEW:TEST:",memberResponse.toString())
            //binding.accountInfo = memberResponse
        })




        binding.editAccountInfoBtn.setOnClickListener{
            Log.d("onclick:", "editAccountInfoBtn")
            navController.navigate(R.id.action_navigation_myAccount_to_editAccountInfoFragment)
        }
        binding.editDeliveryAddressBtn.setOnClickListener{
            Log.d("onclick:", "editDeliveryAddressBtn")

        }
        binding.editPasswordBtn.setOnClickListener {
            Log.d("onclick:", "editPasswordBtn")
            navController.navigate(R.id.action_navigation_myAccount_to_editPasswordFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }


}