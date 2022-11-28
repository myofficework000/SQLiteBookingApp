package com.learning.appointmentbookingapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.learning.appointmentbookingapp.R

import com.learning.appointmentbookingapp.entity.Patient

class PatientSpinnerAdapter(val context: Context, private val patientList:ArrayList<Patient>): BaseAdapter() {
    override fun getCount()=patientList.size

    override fun getItem(p0: Int)=patientList[p0]

    override fun getItemId(p0: Int)=p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView: View? = p1
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_item,p2,false)

        }

        val patients = getItem(p0) as Patient
        convertView?.apply {
            val text = findViewById<TextView>(R.id.txtName)

            patients.apply {
                text.text = patientList[p0].name
            }
        }
        return convertView!!
    }


}