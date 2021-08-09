package com.rightdirection.megapet.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rightdirection.megapet.R
import com.rightdirection.megapet.model.MenuItem

class MenuAdapter(private val context: Activity,private val arrayList: List<MenuItem>): ArrayAdapter<MenuItem>(context,
    R.layout.menu_list_item_layout,arrayList){

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.menu_list_item_layout,null)

        val menuItemIcon = view.findViewById<ImageView>(R.id.menuItemIcon)
        val menuItemTitle = view.findViewById<TextView>(R.id.menuItemTitle)
        val menuItemDesc = view.findViewById<TextView>(R.id.menuItemDesc)

        menuItemIcon.setImageResource(arrayList[position].imageId)
        menuItemTitle.text = arrayList[position].menuItemTitle
        menuItemDesc.text = arrayList[position].menuItemDesc


        return view
    }
}