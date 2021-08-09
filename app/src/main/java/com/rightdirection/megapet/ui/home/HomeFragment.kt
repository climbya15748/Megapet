package com.rightdirection.megapet.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rightdirection.megapet.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var mHomeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val mHandler = Handler(Looper.getMainLooper())
    private var mHandlerRunning:Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        





        return root
    }

    override fun onDestroyView() {

        /* */
        mHandler.removeCallbacksAndMessages(null)
        mHandlerRunning = false
        Log.d("mHandlerRunning","false")
        /* */

        super.onDestroyView()
        _binding = null
    }






}