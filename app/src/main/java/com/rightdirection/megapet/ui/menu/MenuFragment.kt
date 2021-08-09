package com.rightdirection.megapet.ui.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rightdirection.megapet.R
import com.rightdirection.megapet.adapter.MenuAdapter
import com.rightdirection.megapet.databinding.MenuFragmentBinding
import com.rightdirection.megapet.model.MenuItemData

class MenuFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var mMenuViewModel: MenuViewModel
    private lateinit var navController: NavController
    private  var _binding : MenuFragmentBinding ?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mMenuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = MenuFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root

        //binding.menuListView.isClickable = true
        binding.menuListView.adapter = MenuAdapter(requireActivity(),MenuItemData.function)

        binding.menuListView.onItemClickListener = this




        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }





    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.d("onItemClick:","p0: $p0 / p1: $p1 / p2: $p2 / p3: $p3")
        when (p2){
            1->{
                navController.navigate(R.id.action_navigation_menu_to_navigation_dashboard)
            }
        }
    }

}