package com.rightdirection.megapet.ui.member

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentMemberDashboardBinding
import com.rightdirection.megapet.model.member.Member
import com.rightdirection.megapet.preferences.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : Fragment() {


    val viewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentMemberDashboardBinding? = null
    private lateinit var navController: NavController
    @Inject
    lateinit var preference: PreferenceManager


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMemberDashboardBinding.inflate(inflater, container, false)

        preference = PreferenceManager(requireContext())

        binding.loginBtn.setOnClickListener{
            Log.d("onClick:","loginBtn")

            //navController.navigate(R.id.action_navigation_member_dashboard_to_myAccountFragment)

            if (isEmailEmpty()){
                binding.loginEmailInputLayout.isErrorEnabled = true
                binding.loginEmailInputLayout.error=getString(R.string.login_email_error_hint)
            }

            if (isPasswordEmpty()){
                binding.loginPasswordInputLayout.isErrorEnabled = true
                binding.loginPasswordInputLayout.error=getString(R.string.login_password_error_hint)
            }

            if (!isEmailEmpty() && !isPasswordEmpty() ){
                binding.progressBar.visibility = View.VISIBLE
                 val loginDetail = Member(
                    null,
                    null,null,
                    binding.loginPassowrdInput.text.toString(),
                    binding.loginEmailInput.text.toString()
                )
                viewModel.login(loginDetail)

            }

        }


        preference.emailFlow.asLiveData().observe(viewLifecycleOwner, { email ->
            if (!email.isNullOrEmpty()){
                binding.loginEmailInput.setText(email)
            }
        })

        preference.passwordFlow.asLiveData().observe(viewLifecycleOwner, Observer { password->
            if (!password.isNullOrEmpty()){
                binding.loginPassowrdInput.setText(password)
            }
        })

        viewModel.loginResponse.observe(viewLifecycleOwner,{ response->
            if (response.isSuccessful){
                val loginResponseBody = response.body()!!
                //Log.d("login",loginResponseBody!!.login_token.toString())
                viewModel.saveAuthToken(
                    loginResponseBody.login_token.toString(),
                    binding.loginEmailInput.text.toString(),
                    binding.loginPassowrdInput.text.toString()
                )

                navController.navigate(R.id.action_navigation_member_dashboard_to_myAccountFragment)

            }else{
                Log.d("error body:",response.toString())

                MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.login_failure_message_desc))
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                    // Respond to positive button press
                }.show()
            }

            binding.progressBar.visibility = View.INVISIBLE

        })



        binding.registerBtn.setOnClickListener{
            navController.navigate(R.id.action_navigation_memberDashboard_to_registerSelectionFragment)
        }

        binding.loginForgetPasswordBtn.setOnClickListener {
            navController.navigate(R.id.action_navigation_memberDashboard_to_navigation_forgetPasswordFragment)
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


    private fun isEmailEmpty():Boolean {
        return  binding.loginEmailInput.text.isNullOrEmpty()
    }

    private fun isPasswordEmpty():Boolean {
        return binding.loginPassowrdInput.text.isNullOrEmpty()
    }



}