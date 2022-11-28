package com.learning.appointmentbookingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.appointmentbookingapp.databinding.ViewHolderDoctorBinding
import com.learning.appointmentbookingapp.entity.Doctor

class DoctorAdapter(val doctors: ArrayList<Doctor>) : RecyclerView.Adapter<DoctorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderDoctorBinding.inflate(inflater, parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(doctors[position])

        holder.binding.btnDelete.setOnClickListener {
            if (this::deleteListener.isInitialized) {
                deleteListener(doctors[position], position)
            }
        }

        holder.binding.btnUpdate.setOnClickListener {
            if (this::editListener.isInitialized) {
                editListener(doctors[position], position)
            }
        }
    }

    override fun getItemCount() = doctors.size

    private lateinit var deleteListener: (Doctor, Int) -> Unit
    fun setOnDeleteClickListener(listener: (Doctor, Int) -> Unit) {
        deleteListener = listener
    }

    private lateinit var editListener: (Doctor, Int) -> Unit
    fun setOnEditClickListener(listener: (Doctor, Int) -> Unit) {
        editListener = listener
    }
}