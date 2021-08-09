package com.rightdirection.megapet.ui.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.databinding.FragmentEditAccountInfoBinding
import com.rightdirection.megapet.repository.Repository
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditAccountInfoFragment : Fragment() {

    val viewModel: MyAccountViewModel2 by viewModels()

    private var _binding : FragmentEditAccountInfoBinding?= null
    private lateinit var navController : NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditAccountInfoBinding.inflate(inflater, container, false)

        viewModel.memberResponse.observe(viewLifecycleOwner,{memberResponse->
            Log.d("NEW:TEST:",memberResponse.toString())
            binding.accountInfo = memberResponse
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

}