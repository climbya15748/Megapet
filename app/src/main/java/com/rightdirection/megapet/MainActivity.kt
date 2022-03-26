package com.rightdirection.megapet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rightdirection.megapet.databinding.ActivityMainBinding
import com.rightdirection.megapet.preferences.PreferenceManager
import com.rightdirection.megapet.ui.menu.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preference: PreferenceManager
    val viewModel : MenuViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val currentLocale = sharedPreference.getString("localeCode","")
        updateResources(this,currentLocale)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_home, R.id.navigation_memberDashboard, R.id.navigation_notifications))
//        //setupActionBarWithNavController(navController, appBarConfiguration)


        binding.navView.setupWithNavController(navController)


        binding.shoppingCartBtn.setOnClickListener {
            Log.d("onclick:","shoppingCartBtn")
            val intent = Intent(this,ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        if(currentLocale == "en"){
            binding.fab.setImageResource(R.drawable.btn_member_card_en)
        }

        binding.fab.setOnClickListener {

            //making bot nav menu item unchecked
            binding.navView.menu.setGroupCheckable(0,true,false)
            for (i in 0 until  binding.navView.menu.size()){
                binding.navView.menu.getItem(i).isChecked = false
            }
            binding.navView.menu.setGroupCheckable(0,true,true)
            //making bot nav menu item unchecked

            navController.navigate(R.id.navigation_memberCardFragment)

        }

        binding.marqueeTextview.isSelected = true




        //place this block of code first before generating view


        //place this block of code first before generating view


    }


    private fun updateResources(context: Context, localeCode:String?){

        if (localeCode == null){
            return
        }

        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        Log.d("updateResources:","locale is $locale ")

        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        res.updateConfiguration(config, res.displayMetrics)
        //this.setContentView(R.layout.activity_main)
        //this.recreate()

    }





}