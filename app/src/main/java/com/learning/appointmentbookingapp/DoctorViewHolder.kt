package com.learning.appointmentbookingapp

import androidx.recyclerview.widget.RecyclerView
import com.learning.appointmentbookingapp.databinding.ViewHolderDoctorBinding
import com.learning.appointmentbookingapp.entity.Doctor

class DoctorViewHolder(val binding: ViewHolderDoctorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(doctor: Doctor) {
        binding.apply {
            tvDoctorName.text = doctor.name
            tvMobileNr.text = "Mobile nr: ${doctor.mobileNo}"
        }
    }
}