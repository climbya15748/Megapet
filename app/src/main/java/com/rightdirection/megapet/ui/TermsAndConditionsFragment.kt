package com.rightdirection.megapet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentRegisterBinding
import com.rightdirection.megapet.databinding.FragmentTermsAndConditionsBinding


class TermsAndConditionsFragment : Fragment() {

    private var _binding : FragmentTermsAndConditionsBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTermsAndConditionsBinding.inflate(inflater, container, false)


        return binding.root
    }

}