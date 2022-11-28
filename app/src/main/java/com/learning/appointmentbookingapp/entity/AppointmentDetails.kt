package com.learning.appointmentbookingapp.entity

data class AppointmentDetails(
    val appNo: Long,
    val doctorId: Long,
    val doctorName: String,
    val doctorMobNo: String,
    val patientId: Long,
    val patientName: String,
    val patientMobNo: String,
    val aptDateTime: Long
)
