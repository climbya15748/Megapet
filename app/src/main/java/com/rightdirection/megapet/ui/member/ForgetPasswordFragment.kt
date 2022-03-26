package com.rightdirection.megapet.ui.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.ForgetPasswordFragmentBinding
import com.rightdirection.megapet.model.member.Member
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : Fragment() {



    val viewModel: ForgetPasswordViewModel by viewModels()
    private var _binding: ForgetPasswordFragmentBinding? = null
    private lateinit var navController: NavController


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ForgetPasswordFragmentBinding.inflate(inflater, container, false)

        binding.radioForgetEmail.setOnClickListener {
            binding.emailForgetPasswordInputLayout.isErrorEnabled = false
            binding.phoneForgetPasswordInputLayout.isErrorEnabled = false
            binding.phoneForgetPasswordInput.text?.clear()
        }

        binding.radioForgetPhone.setOnClickListener{
            binding.emailForgetPasswordInputLayout.isErrorEnabled = false
            binding.phoneForgetPasswordInputLayout.isErrorEnabled = false
            binding.emailForgetPasswordInput.text?.clear()
        }

        binding.emailForgetPasswordInput.doOnTextChanged { _, _, _, _ ->
            binding.emailForgetPasswordInputLayout.isErrorEnabled = false
        }
        binding.phoneForgetPasswordInput.doOnTextChanged { _, _, _, _ ->
            binding.phoneForgetPasswordInputLayout.isErrorEnabled = false
        }

        binding.forgetPasswordSubmitBtn.setOnClickListener {

            if (binding.radioForgetEmail.isChecked){
                if (isEmailEmpty()){
                    binding.emailForgetPasswordInputLayout.isErrorEnabled = true
                    binding.emailForgetPasswordInputLayout.error=getString(R.string.login_email_error_hint)
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                    sendRequestByEmail()
                }

            }

            if (binding.radioForgetPhone.isChecked){
                if (isPhoneEmpty()){
                    binding.phoneForgetPasswordInputLayout.isErrorEnabled = true
                    binding.phoneForgetPasswordInputLayout.error=getString(R.string.register_phone_error_hint)
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                    sendRequestByPhone()
                }
            }

        }

        viewModel.forgetPasswordResponse.observe(viewLifecycleOwner,{ it->
            if (it.isSuccessful){
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("重設密碼的指示已寄往你的郵箱")
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        // Respond to positive button press
                    }.show()
                navController.navigate(R.id.action_navigation_forgetPasswordFragment_to_navigation_memberDashboard)
            }else{
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("網絡錯誤 .forget-password 500")
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        // Respond to positive button press
                    }.show()
            }
            binding.progressBar.visibility = View.INVISIBLE
        })

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


    private fun isEmailEmpty():Boolean {
        return  binding.emailForgetPasswordInput.text.isNullOrEmpty()
    }

    private fun isPhoneEmpty():Boolean {
        return  binding.phoneForgetPasswordInput.text.isNullOrEmpty()
    }

    private fun sendRequestByEmail(){

        val forgetPasswordDetail = Member(
            null,
            null,null,
            null,
            binding.emailForgetPasswordInput.text.toString()
        )
        viewModel.forgetPassword(forgetPasswordDetail)

    }

    private fun sendRequestByPhone(){

        val forgetPasswordDetail = Member(
            null,
            null,null,
            null,
            null,
            null,
            null,
            null,
            binding.phoneForgetPasswordInput.text.toString()
        )
        viewModel.forgetPassword(forgetPasswordDetail)

    }


}