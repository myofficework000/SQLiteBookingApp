package com.learning.appointmentbookingapp

object Constant {
    const val DB_NAME = "Appointment"
    const val DB_VERSION = "1"

    val CREATE_DOCTOR_TABLE = """CREATE TABLE doctor (
        doctorId INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        mobileNo TEXT,
        gender TEXT
        
    )""".trimMargin()

    val CREATE_PATIENT_TABLE = """CREATE TABLE patient (
        patientId INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        gender TEXT,
         mobileNo TEXT
    )""".trimMargin()


    val CREATE_APPOINTMENT_TABLE = """CREATE TABLE appointment (
        appNo INTEGER PRIMARY KEY AUTOINCREMENT,
        doctorId INTEGER,
        patientId INTEGER,
        appDateTime:INTEGER
    )""".trimMargin()
}