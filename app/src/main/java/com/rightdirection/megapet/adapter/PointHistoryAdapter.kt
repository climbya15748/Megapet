package com.rightdirection.megapet.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.rightdirection.megapet.R
import com.rightdirection.megapet.model.member.PointHistory
import com.rightdirection.megapet.model.menu.MenuItem

class PointHistoryAdapter (
    private val context: Activity,
    private val arrayList: List<PointHistory>
    ): ArrayAdapter<PointHistory>(context,R.layout.point_history_list_item_layout,arrayList) {


    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.point_history_list_item_layout,null)

        val memberID = view.findViewById<TextView>(R.id.member_id)
        val amount = view.findViewById<TextView>(R.id.amount)
        val expireOn = view.findViewById<TextView>(R.id.expire_on)

        memberID.text = arrayList[position].member_id
        amount.text = arrayList[position].amount.toString()
        expireOn.text = arrayList[position].expire_on


        return view
    }

    }


