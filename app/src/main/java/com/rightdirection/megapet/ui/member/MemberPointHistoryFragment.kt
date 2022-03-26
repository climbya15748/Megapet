package com.rightdirection.megapet.ui.member

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.adapter.PointHistoryAdapter


import com.rightdirection.megapet.databinding.FragmentMemberPointHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberPointHistoryFragment : Fragment() {



    val viewModel : MemberPointHistoryViewModel by viewModels()
    private var _binding: FragmentMemberPointHistoryBinding? = null

    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMemberPointHistoryBinding.inflate(inflater, container, false)


        viewModel.isLogin.observe(viewLifecycleOwner, { haveToken->
            if (!haveToken){
                navController.navigate(R.id.action_navigation_memberPaymentScanFragment_to_navigation_memberDashboard)
            }else{
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getPointHistory()
                viewModel.pointHistoryResponse.observe(viewLifecycleOwner,{ response->
                    if (response.isSuccessful){
                        Log.d("pointHistoryResponse",response.body().toString())
                        if(response.body() == null){
                            binding.noPointHistoryText.visibility = View.VISIBLE
                        }else{
                            binding.pointHistoryListView.adapter =
                                response.body()?.let { PointHistoryAdapter(requireActivity(), it) }
                        }
                    }else{
                        Log.e("Error:",response.code().toString())
                        Log.e("Error:",response.body().toString())
                    }
                    binding.progressBar.visibility = View.INVISIBLE
                })
            }
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








}