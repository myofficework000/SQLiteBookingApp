package com.learning.appointmentbookingapp.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.learning.appointmentbookingapp.entity.Doctor

class DoctorDao(context: Context) {

    private val dbHelper = DbHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addDoctor(doctor: Doctor): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", doctor.name)
            put("mobileNo", doctor.mobileNo)
        }
        return db.insert("doctor", null, contentValues)
    }

    @SuppressLint("Recycle")
    fun getAllDoctors(): ArrayList<Doctor> {
        val doctorList = ArrayList<Doctor>()
        val cursor: Cursor = db.query("doctor", null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val doctorId = cursor.getLong(0)
            val doctorName = cursor.getString(1)
            val mobNo = cursor.getString(2)
            val doctor = Doctor(doctorId, doctorName, mobNo)
            doctorList.add(doctor)
        }
        return doctorList
    }

    fun deleteDoctor(doctorId: Long): Boolean {
        val numOfRowsDeleted = db.delete("doctor", "doctorId=$doctorId", null)
        return numOfRowsDeleted == 1
    }

    fun updateDoctor(doctor: Doctor): Boolean {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", doctor.name)
            put("mobileNo", doctor.mobileNo)
        }
        val numOfRowUpdated =
            db.update("doctor", contentValues, "doctorId=${doctor.doctorId}", null)
        return numOfRowUpdated == 1
    }
}