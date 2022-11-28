package com.learning.appointmentbookingapp.entity

data class Appointment(
    val appNo: Long,
    val doctorId: Long,
    val patientId: Long,
    val aptDateTime: Long
)
