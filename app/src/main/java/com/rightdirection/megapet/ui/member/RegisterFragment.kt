package com.rightdirection.megapet.ui.member


import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.R.string.*
import com.rightdirection.megapet.databinding.FragmentRegisterBinding
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.model.member.ObjPhone
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class RegisterFragment : Fragment() {



    val viewModel: RegisterViewModel by viewModels()
    private var _binding : FragmentRegisterBinding ?= null
    private val binding get() = _binding!!

    private lateinit var navController : NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val signUpBy = arguments?.getString("signUpBy")

        if (signUpBy == "EMAIL"){
            binding.phoneRegisterInputLayout.hint = getString(register_phone_hint_optional)
        }
        if (signUpBy == "PHONE"){
            binding.registerPhoneVerificationLayout.visibility = View.VISIBLE
            binding.emailRegisterInputLayout.hint = getString(register_email_hint_optional)
        }

        //Log.d("signUpBy",signUpBy.toString())


        binding.emailRegisterInput.doOnTextChanged { text, _, _, _ ->
            isEmailValid(text)
        }
        binding.passwordRegisterInput.doOnTextChanged {text, _, _, _ ->
            isPasswordValid(text)
        }
        binding.confirmPasswordRegisterInput.doOnTextChanged {text, _, _, _ ->
            isConfirmPasswordValid(text)
        }
        binding.firstnameRegisterInput.doOnTextChanged {text, _, _, _ ->
            isFirstnameValid(text)
        }
        binding.lastnameRegisterInput.doOnTextChanged {text, _, _, _ ->
            isLastnameValid(text)
        }
        binding.phoneRegisterInput.doOnTextChanged {text, _, _, _ ->
            isPhoneValid(text)
        }
        binding.otpRegisterInput.doOnTextChanged {text, _, _, _ ->
            isOtpValid(text)
        }
        binding.registerAgreeCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                binding.registerAgreeCheckbox.error = null
                binding.registerAgreeCheckbox.clearFocus()
            }
        }


        binding.getMemberOtpBtn.setOnClickListener {
            val isPhoneValid = isPhoneValid(binding.phoneRegisterInput.text)
            if (isPhoneValid){
                getOtpViaPhone()
            }
        }


        binding.registerSubmitBtn.setOnClickListener {

            //val isEmailValid = isEmailValid(binding.emailRegisterInput.text)
            val isPasswordValid = isPasswordValid(binding.passwordRegisterInput.text)
            val isConfirmPasswordValid = isConfirmPasswordValid(binding.confirmPasswordRegisterInput.text)
            val isFirstnameValid = isFirstnameValid(binding.firstnameRegisterInput.text)
            val isLastnameValid = isLastnameValid(binding.lastnameRegisterInput.text)
            //val isPhoneValid = isPhoneValid(binding.phoneRegisterInput.text)
            val isCheckboxChecked = isCheckboxChecked()
            val isOtpValid = isOtpValid(binding.otpRegisterInput.text)

            if (signUpBy == "EMAIL"){
                if (isEmailValid(binding.emailRegisterInput.text) && isPasswordValid && isConfirmPasswordValid && isFirstnameValid  && isCheckboxChecked ){
                    signUp(signUpBy)
                }
            }
            if (signUpBy == "PHONE"){
                if (isPhoneValid(binding.phoneRegisterInput.text) && isPasswordValid && isConfirmPasswordValid && isFirstnameValid  && isCheckboxChecked && isOtpValid ){
                    signUp(signUpBy)
                }
            }

        }

        viewModel.regOtpResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful){
                val toast = Toast.makeText(activity,getString(send_otp_request_successful),Toast.LENGTH_LONG)
                toast.show()
            }else{
                Log.d("response body:",response.body().toString())
                when (response.code().toString()){
                    "400"->{
                        binding.phoneRegisterInputLayout.isErrorEnabled = true
                        binding.phoneRegisterInputLayout.error = getString(send_otp_request_error_400)
                    }
                    "403"->{
                        val toast = Toast.makeText(activity,getString(send_otp_request_error_403),Toast.LENGTH_LONG)
                        toast.show()
                    }
                    "503"->{
                        val toast = Toast.makeText(activity,getString(send_otp_request_error_503),Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            }
            binding.progressBar.visibility = View.INVISIBLE
        })

        viewModel.registrationResponse.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful){
                val toast = Toast.makeText(activity,getString(register_success_201),Toast.LENGTH_LONG)
                toast.show()
                navController.navigate(R.id.action_navigation_register_to_navigation_memberDashboard)

            }else{
                //Log.d("error body:",response.toString())
                Log.d("response body:",response.body().toString())
                when (response.code().toString()){
                    "400"->{
                        binding.emailRegisterInputLayout.isErrorEnabled = true
                        binding.emailRegisterInputLayout.error = getString(register_error_400)
                    }
                    "401"->{
                        binding.phoneRegisterInputLayout.isErrorEnabled = true
                        binding.phoneRegisterInputLayout.error = getString(register_error_401)
                    }
                    "403"->{
                        binding.otpRegisterInputLayout.isErrorEnabled = true
                        binding.otpRegisterInputLayout.error = getString(verify_otp_error)
                    }
                    "503"->{
                        val toast = Toast.makeText(activity,getString(register_error_503),Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
                binding.progressBar.visibility = View.INVISIBLE
            }

        })

        val ss = SpannableString(getString(register_i_agree_text)).toSpannable()
        val sharedPreference = requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val currentLocale = sharedPreference.getString("localeCode","")
        var startChar = 0
        startChar = if (currentLocale == ""){ 24 }else{ 15 }
        ss[startChar until ss.length+1] = object:ClickableSpan(){
            override fun onClick(p0: View) {
                navController.navigate(R.id.action_navigation_register_to_navigation_termsAndConditionsFragment)
            }
        }
        binding.registerAgreeText.movementMethod = LinkMovementMethod()
        binding.registerAgreeText.text = ss



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }


    private fun getOtpViaPhone(){
        binding.progressBar.visibility = View.VISIBLE
        val body = ObjPhone(binding.phoneRegisterInput.text.toString())
        println("send otp to : $body")
        viewModel.postRegOtpRequest(body)
    }

    private fun signUp(signUpBy:String?){
        //start loading animation
        binding.progressBar.visibility = View.VISIBLE
        val form = Member(null,
            binding.firstnameRegisterInput.text.toString(),
            binding.lastnameRegisterInput.text.toString(),
            binding.passwordRegisterInput.text.toString(),
            binding.emailRegisterInput.text.toString(),
            0.0,
            0.0,
            0.0,
            binding.phoneRegisterInput.text.toString(),null,null,
            signUpBy,
            binding.otpRegisterInput.text.toString()
        )
        Log.d("signUP:","sending form....")
        viewModel.postRegister(form)
    }

    private fun isEmailValid(text: CharSequence?):Boolean{

        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
        return if (!text.isNullOrEmpty() && text.length <=50 && emailPattern.matcher(text).matches()){
            binding.emailRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.emailRegisterInputLayout.isErrorEnabled = true
            binding.emailRegisterInputLayout.error = getString(register_email_error_hint)
            false
        }

    }

    private fun isPasswordValid(text: CharSequence?):Boolean{

        return if(!text.isNullOrEmpty() && text.length <=100 && text.length>=6) {
            binding.passwordRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.passwordRegisterInputLayout.isErrorEnabled = true
            binding.passwordRegisterInputLayout.error = getString(register_password_error_hint)
            false
        }

    }

    private fun isConfirmPasswordValid(text: CharSequence?):Boolean{

        val password = binding.passwordRegisterInput.text


        return if(!text.isNullOrEmpty() && text.toString() == password.toString()) {
            binding.confirmPasswordRegisterInputLayout.isErrorEnabled = false
            true
        }else{
           //binding.confirmPasswordRegisterInputLayout.isErrorEnabled = true
            binding.confirmPasswordRegisterInputLayout.error = getString(
                register_confirm_password_error_hint)
            false
        }

    }


    private fun isFirstnameValid(text:CharSequence?):Boolean{

        return if(!text.isNullOrEmpty() && text.length <=50) {
            binding.firstnameRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.firstnameRegisterInputLayout.isErrorEnabled = true
            binding.firstnameRegisterInputLayout.error = getString(register_firstname_error_hint)
            false
        }

    }

    private fun isLastnameValid(text:CharSequence?):Boolean{

        return if(!text.isNullOrEmpty() && text.length <=50) {
            binding.lastnameRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.lastnameRegisterInputLayout.isErrorEnabled = true
            binding.lastnameRegisterInputLayout.error = getString(register_lastname_error_hint)
            false
        }

    }

    private fun isPhoneValid(text:CharSequence?):Boolean{

        Log.d("phone:",text?.length.toString())
        return if(!text.isNullOrEmpty() && text.length == 8) {
            binding.phoneRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.phoneRegisterInputLayout.isErrorEnabled = true
            binding.phoneRegisterInputLayout.error = getString(register_phone_error_hint)
            false
        }

    }

    private fun isOtpValid(text:CharSequence?):Boolean{

        return if(!text.isNullOrEmpty()) {
            binding.otpRegisterInputLayout.isErrorEnabled = false
            true
        }else{
            binding.otpRegisterInputLayout.isErrorEnabled = true
            binding.otpRegisterInputLayout.error = getString(register_otp_error_hint)
            false
        }

    }

    private fun isCheckboxChecked():Boolean{
        return if(binding.registerAgreeCheckbox.isChecked){
            binding.registerAgreeCheckbox.clearFocus()
            true
        }else{
//            Log.d("checkbox","false")
            binding.registerAgreeCheckbox.error = getString(register_agree_checkbox_error_hint)
            binding.registerAgreeCheckbox.requestFocus()
            false
        }
    }




}