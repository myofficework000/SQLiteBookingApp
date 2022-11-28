package com.learning.appointmentbookingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.appointmentbookingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEvents()
        addDoctor()
    }

    private fun setupEvents() {
        binding.buttonAddAppointment.setOnClickListener {
            startActivity(Intent(this,BookAppointmentActivity::class.java))
        }
    }

    private fun addDoctor() {
        binding.buttonAddDoctor.setOnClickListener {
            startActivity(Intent(this, DoctorActivity::class.java))
        }
    }
}