package com.learning.appointmentbookingapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.learning.appointmentbookingapp.R
import com.learning.appointmentbookingapp.entity.Doctor


class DoctorSpinnerAdapter(val context:Context, private val doctorList: ArrayList<Doctor>) :
    BaseAdapter() {
    override fun getCount()=doctorList.size

    override fun getItem(p0: Int)=doctorList[p0]

    override fun getItemId(p0: Int)=p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView: View? = p1
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_item,p2,false)

        }

        val doctors = getItem(p0) as Doctor
        convertView?.apply {
            val text = findViewById<TextView>(R.id.txtName)

            doctors.apply {
                text.text = doctorList[p0].name
            }
        }
        return convertView!!
    }

}