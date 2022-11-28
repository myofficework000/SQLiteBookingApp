package com.learning.appointmentbookingapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.learning.appointmentbookingapp.dao.DoctorDao
import com.learning.appointmentbookingapp.dao.PatientDao
import com.learning.appointmentbookingapp.databinding.ActivityBookApppintmentBinding
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {
    //Charles

    private lateinit var binding: ActivityBookApppintmentBinding
    private lateinit var doctorAdapter: DoctorSpinnerAdapter
    private lateinit var patientAdapter: PatientSpinnerAdapter
    private lateinit var doctorDao: DoctorDao
    private lateinit var patientDao: PatientDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookApppintmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doctorDao = DoctorDao(this)
        patientDao = PatientDao(this)
        populateSpinners()
        events()
    }

    private fun populateSpinners() {
        populateDoctors()
        populatePatients()
    }

    private fun populatePatients() {
        val patientList = patientDao.getAllPatient()
        patientAdapter = PatientSpinnerAdapter(this, patientList)
        binding.spnPatient.adapter = patientAdapter
    }

    private fun populateDoctors() {
        val doctorList = doctorDao.getAllDoctors()
        doctorAdapter = DoctorSpinnerAdapter(this, doctorList)
        binding.spnDoctor.adapter = doctorAdapter

    }

    private fun events() {
        binding.btnBookAppointment.setOnClickListener {
            Toast.makeText(this, "Appointment Created", Toast.LENGTH_SHORT).show()
        }
        binding.btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            val timePick = TimePickerDialog(
                this,
                { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                    binding.btnTime.text = "$selectedHour:$selectedMinute"
                },
                hour,
                minute,
                false
            )
            timePick.setTitle("Select Time")
            timePick.show()
        }
        binding.btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    binding.btnDate.text = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                },
                year,
                month,
                day
            )

            datePicker.show()
        }
    }


}