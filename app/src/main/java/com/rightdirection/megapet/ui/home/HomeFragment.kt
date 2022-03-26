package com.rightdirection.megapet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.rightdirection.megapet.R
import com.rightdirection.megapet.databinding.FragmentHomeBinding
import com.rightdirection.megapet.preferences.PreferenceManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userPreferences: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        Picasso.get().load(resources.getString(R.string.home_banner_url_1)).into(binding.homeBanner1)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_2)).into(binding.homeBanner2)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_3)).into(binding.homeBanner3)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_4)).into(binding.homeBanner4)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_5)).into(binding.homeBanner5)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_6)).into(binding.homeBanner6)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_7)).into(binding.homeBanner7)
//        Picasso.get().load(resources.getString(R.string.home_banner_url_8)).into(binding.homeBanner8)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_1)).into(binding.homeBanner1)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_2)).into(binding.homeBanner2)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_3)).into(binding.homeBanner3)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_4)).into(binding.homeBanner4)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_5)).into(binding.homeBanner5)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_6)).into(binding.homeBanner6)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_7)).into(binding.homeBanner7)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_8)).into(binding.homeBanner8)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_9)).into(binding.homeBanner9)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_10)).into(binding.homeBanner10)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_11)).into(binding.homeBanner11)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_12)).into(binding.homeBanner12)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_13)).into(binding.homeBanner13)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_14)).into(binding.homeBanner14)
        Glide.with(this).load(resources.getString(R.string.home_banner_url_15)).into(binding.homeBanner15)


//        userPreferences = LoginPreferenceManager(requireContext())
//
//        userPreferences.jwtFlow.asLiveData().observe(viewLifecycleOwner, Observer {
//            Log.d("liveData:",it?:"Null")
//        })
//        userPreferences.emailFlow.asLiveData().observe(viewLifecycleOwner, Observer {
//            Log.d("liveData:",it?:"Null")
//        })
//        userPreferences.passwordFlow.asLiveData().observe(viewLifecycleOwner, Observer {
//            Log.d("liveData:",it?:"Null")
//        })
//


        return binding.root
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }







}