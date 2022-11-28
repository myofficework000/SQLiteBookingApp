package com.learning.appointmentbookingapp.ui

import com.learning.appointmentbookingapp.DoctorAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.appointmentbookingapp.dao.DoctorDao
import com.learning.appointmentbookingapp.databinding.ActivityDoctorBinding
import com.learning.appointmentbookingapp.entity.Doctor

class DoctorActivity : AppCompatActivity() {
    //Chris
    lateinit var binding: ActivityDoctorBinding
    private lateinit var doctorDao: DoctorDao
    private lateinit var allDoctors: ArrayList<Doctor>
    private lateinit var doctorsAdapter: DoctorAdapter

    private var selectedDoctorId: Long = -1
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDoctors.layoutManager = LinearLayoutManager(baseContext)
        doctorDao = DoctorDao(baseContext)

        setupEvents()
    }

    private fun setupEvents() {
        binding.btnAddDoctor.setOnClickListener {
            saveDoctor()
        }

        binding.btnShowAllDoctors.setOnClickListener {
            showAllDoctors()
        }

        binding.btnUpdateDoctor.setOnClickListener {
            updateDoctors()
        }
    }

    private fun saveDoctor() {
        binding.apply {
            val name = etDoctorName.text.toString()
            val mobileNr = etMobileNr.text.toString()

            val doctor = Doctor(0, name, mobileNr)

            val doctorId = doctorDao.addDoctor(doctor)

            if (doctorId > 0) {
                Toast.makeText(
                    baseContext,
                    "Doctor stored with id= $doctorId",
                    Toast.LENGTH_SHORT
                ).show()

                etDoctorName.text.clear()
                etMobileNr.text.clear()


            } else {
                Toast.makeText(baseContext, "Failed to save doctor info", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showAllDoctors() {
        allDoctors = doctorDao.getAllDoctors()
        doctorsAdapter = DoctorAdapter(allDoctors)
        binding.rvDoctors.adapter = doctorsAdapter

        doctorsAdapter.setOnDeleteClickListener { doctor, position ->
            deleteDoctor(doctor, position)
        }

        doctorsAdapter.setOnEditClickListener { doctor, position ->
            binding.etDoctorName.setText(doctor.name)
            binding.etMobileNr.setText(doctor.mobileNo)


            selectedDoctorId = doctor.doctorId
            selectedPosition = position
        }
    }

    private fun updateDoctors() {
        binding.apply {
            val newName = etDoctorName.text.toString()
            val newMobileNr = etMobileNr.text.toString()

            val newDoctor = Doctor(selectedDoctorId, newName, newMobileNr)
            val updated = doctorDao.updateDoctor(newDoctor)

            if (updated) {
                Toast.makeText(
                    baseContext,
                    "Doctor info updated successfully",
                    Toast.LENGTH_SHORT
                ).show()
                allDoctors.set(selectedPosition, newDoctor)
                doctorsAdapter.notifyItemChanged(selectedPosition)
            } else {
                Toast.makeText(
                    baseContext,
                    "Failed to update doctor info. Please try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun deleteDoctor(doctor: Doctor, position: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirm action")
            .setMessage("Are you sure you want to delete this record?")
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, which ->

                val deleted = doctorDao.deleteDoctor(doctor.doctorId)

                dialog.dismiss()
                if (deleted) {
                    allDoctors.removeAt(position)
                    doctorsAdapter.notifyItemRemoved(position)
                    Toast.makeText(
                        baseContext,
                        "Doctor deleted successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Failed to delete doctor record",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }.create()

        dialog.show()
    }
}