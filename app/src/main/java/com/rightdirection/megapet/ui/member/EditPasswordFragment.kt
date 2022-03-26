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
import com.rightdirection.megapet.databinding.FragmentEditPasswordBinding
import com.rightdirection.megapet.model.member.ObjEditPassword
import com.rightdirection.megapet.ui.menu.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditPasswordFragment : Fragment() {

    val viewModel : MyAccountViewModel by viewModels()
    val menuViewModel : MenuViewModel by viewModels()
    private lateinit var navController: NavController
    private var _binding : FragmentEditPasswordBinding?= null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentEditPasswordBinding.inflate(inflater, container, false)

        binding.editPasswordSubmitBtn.setOnClickListener {
            if (isCurrentPasswordValid() && isPasswordValid() && isConfirmPasswordValid() ){
                val form = ObjEditPassword(binding.editCurrentPasswordInput.text.toString(),binding.editNewPasswordInput.text.toString())
                viewModel.postUpdatePassword(form)
            }else{
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(resources.getString(R.string.edit_password_password_error_hint))
                    .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                        // Respond to positive button press
                    }.show()
            }

        }

        viewModel.updatePasswordResponse.observe(viewLifecycleOwner,{
            if (it.isSuccessful){

                Toast.makeText(activity, resources.getString(R.string.edit_password_successful), Toast.LENGTH_SHORT).show()
                menuViewModel.logout()
                navController.navigate(R.id.action_navigation_editPasswordFragment_to_navigation_memberDashboard)
            }else{
                if(it.code().toString()=="400"){
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage(resources.getString(R.string.edit_password_current_password_error_hint))
                        .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                            // Respond to positive button press
                        }.show()
                }
                Log.e("Error:",it.code().toString())
                Log.e("Error:",it.errorBody().toString())
            }
        })




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun isCurrentPasswordValid():Boolean{
        return !binding.editCurrentPasswordInput.text.isNullOrEmpty()
    }

    private fun isPasswordValid():Boolean{
        val newPassword = binding.editNewPasswordInput.text
        return (newPassword.length in 6..100 &&  !newPassword.isNullOrEmpty() )
    }

    private fun isConfirmPasswordValid():Boolean{
        val newPassword = binding.editNewPasswordInput.text
        val confirmPassword = binding.editConfirmPasswordInput.text
        return (newPassword.toString() == confirmPassword.toString())
    }


}