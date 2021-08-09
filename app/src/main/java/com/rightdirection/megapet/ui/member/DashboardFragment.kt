package com.rightdirection.megapet.ui.member

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentMemberDashboardBinding

class DashboardFragment : Fragment(),View.OnClickListener {

    private lateinit var mDashboardViewModel: DashboardViewModel
    private var _binding: FragmentMemberDashboardBinding? = null
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mDashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentMemberDashboardBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)




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

    override fun onClick(v: View?) {

        Log.d("onclick",v!!.id.toString())

        when (v.id){
            R.id.login_btn->{
                navController.navigate(R.id.action_navigation_member_dashboard_to_myAccountFragment)
            }
            R.id.register_btn->{
                navController.navigate(R.id.action_navigation_member_dashboard_to_registerFragment)
            }
        }




    }

}