package com.rightdirection.megapet.ui.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentEditAccountInfoBinding
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.model.member.ObjOtp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditAccountInfoFragment : Fragment() {

    val viewModel: MyAccountViewModel by viewModels()

    private var _binding : FragmentEditAccountInfoBinding?= null
    private lateinit var navController : NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditAccountInfoBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.VISIBLE

        viewModel.memberResponse.observe(viewLifecycleOwner) { memberResponse ->
            if (memberResponse.isSuccessful) {
                binding.accountInfo = memberResponse.body()
                //if phone not verified, show phone layout
                if (binding.accountInfo!!.is_phone_valid == "0") {
                    binding.editPhoneVerificationLayout.visibility = View.VISIBLE
                    //if sign up by phone , cannot change phone number to prevent duplicate
                    if (binding.accountInfo!!.sign_up_by == "PHONE") {
                        binding.editPhoneInput.isFocusable = false
                    }
                }
            } else {
                Log.e("Error:", memberResponse.code().toString())
                Log.e("Error:", memberResponse.errorBody().toString())
            }
            binding.progressBar.visibility = View.INVISIBLE
        }



        viewModel.updateInfoResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.edit_info_successful),
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(R.id.action_navigation_editAccountInfoFragment_to_navigation_myAccount)
            } else {
                Log.e("Error:", it.code().toString())
                Log.e("Error:", it.errorBody().toString())
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        viewModel.sendOtpRequestResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.send_otp_request_successful),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (it.code().toString() == "400") {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.send_otp_request_error_400),
                        Toast.LENGTH_LONG
                    ).show()
                }
                if (it.code().toString() == "403") {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.send_otp_request_error_403),
                        Toast.LENGTH_LONG
                    ).show()
                }
                if (it.code().toString() == "503") {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.send_otp_request_error_503),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        viewModel.sendOtpVerificationResponse.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.verify_otp_successful),
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(R.id.action_navigation_editAccountInfoFragment_to_navigation_myAccount)
            } else {
                if (it.code().toString() == "400") {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.verify_otp_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            binding.progressBar.visibility = View.INVISIBLE
        }

        //submit btn

        binding.editAccountInfoSubmitBtn.setOnClickListener {

            if (isFirstNameEmpty() ){
                MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.edit_info_failure_message_desc))
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                    // Respond to positive button press
                }.show()
            }else{

                binding.progressBar.visibility = View.VISIBLE

                val form = Member(null,
                    binding.editFirstnameInput.text.toString(),
                    "",
                    null,
                    null,
                    null,
                    null,
                    null,
                    binding.editPhoneInput.text.toString()
                )

                viewModel.postUpdateMemberInfo(form)
            }
        }

        binding.getMemberOtpBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.postOtpRequest()
        }

        binding.verifyOtpBtn.setOnClickListener {
            if (binding.editOtpInput.text.isNullOrEmpty()){
                binding.editOtpInput.error = ""
            }else{
                binding.progressBar.visibility = View.VISIBLE
                val otp:ObjOtp = ObjOtp(binding.editOtpInput.text.toString())
                viewModel.postOtpVerification(otp)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun isFirstNameEmpty():Boolean {
        return binding.editFirstnameInput.text.isNullOrEmpty()
    }

    private fun isLastNameEmpty():Boolean {
        return binding.editLastnameInput.text.isNullOrEmpty()
    }

}