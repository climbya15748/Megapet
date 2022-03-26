package com.rightdirection.megapet.ui.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentMemberDashboardBinding
import com.rightdirection.megapet.databinding.FragmentRegisterBinding
import com.rightdirection.megapet.databinding.FragmentRegisterSelectionBinding


class RegisterSelectionFragment : Fragment() {

    private var _binding: FragmentRegisterSelectionBinding ?= null
    private lateinit var navController: NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterSelectionBinding.inflate(inflater, container, false)

        binding.registerSelectEmailBtn.setOnClickListener {
            val bundle = bundleOf("signUpBy" to "EMAIL")
            navController.navigate(R.id.action_registerSelectionFragment_to_navigation_register,bundle)
        }

        binding.registerSelectPhoneBtn.setOnClickListener {
            val bundle = bundleOf("signUpBy" to "PHONE")
            navController.navigate(R.id.action_registerSelectionFragment_to_navigation_register,bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}