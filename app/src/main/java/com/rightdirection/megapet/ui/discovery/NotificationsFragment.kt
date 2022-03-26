package com.rightdirection.megapet.ui.discovery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentDiscoverBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentDiscoverBinding? = null

    private lateinit var navController : NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)




        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        navController.navigate(R.id.action_navigation_notifications_to_navigation_home)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(resources.getString(R.string.online_shop_website))
        Log.d("open webpage:",resources.getString(R.string.online_shop_website))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}