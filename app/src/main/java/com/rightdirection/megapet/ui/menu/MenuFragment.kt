package com.rightdirection.megapet.ui.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rightdirection.megapet.R
import com.rightdirection.megapet.adapter.MenuAdapter
import com.rightdirection.megapet.databinding.MenuFragmentBinding
import com.rightdirection.megapet.model.menu.MenuItemData
import com.rightdirection.megapet.preferences.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MenuFragment : Fragment(), AdapterView.OnItemClickListener {

    val viewModel : MenuViewModel by viewModels()
    private lateinit var navController: NavController
    private var _binding : MenuFragmentBinding ?= null
    private val binding get() = _binding!!
    private var isLogin:Boolean = false



    @Inject
    lateinit var preference: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = MenuFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root

        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {
                binding.menuListView.adapter =
                    MenuAdapter(requireActivity(), MenuItemData.functionListWithLogin)
            } else {
                binding.menuListView.adapter =
                    MenuAdapter(requireActivity(), MenuItemData.functionList)
            }
            isLogin = it
        }


        binding.menuListView.onItemClickListener = this


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }



    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //Log.d("onItemClick:","p0: $p0 / p1: $p1 / p2: $p2 / p3: $p3")
        when (p2){
            //0->{ Toast.makeText(activity, "即將推出 敬請期待",Toast.LENGTH_SHORT).show() }
            0->{
                if (isLogin){
                    navController.navigate(R.id.action_navigation_menu_to_navigation_myAccount)
                }else{
                    navController.navigate(R.id.action_navigation_menu_to_navigation_dashboard)
                }

            }
            1->{
                val sharedPreference = requireActivity().getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
                val currentLocale = sharedPreference.getString("localeCode","")
                Log.d("currentLocale","in fragment is $currentLocale")
                var newLocaleCode =""
                newLocaleCode = if (currentLocale == ""){
                    Log.d("Language updated to :","en")
                    "en"
                } else{
                    Log.d("Language updated to :","hk")
                    ""
                }

                val editor = sharedPreference.edit()
                editor.putString("localeCode",newLocaleCode)
                editor.apply()

                requireActivity().recreate()

            }
            //2->{ Toast.makeText(activity, "即將推出 敬請期待",Toast.LENGTH_SHORT).show() }
            //3->{ Toast.makeText(activity, "即將推出 敬請期待",Toast.LENGTH_SHORT).show() }
            2->{
                if (isLogin){
                    //confirm logout
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage(resources.getString(R.string.confirm_logout_text))
                        .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                            //logout
                            viewModel.logout()
                            Toast.makeText(activity, resources.getString(R.string.logout_successful),Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton(resources.getString(R.string.cancel)){ _, _ ->

                        }
                        .show()


                }
            }
        }
    }

}