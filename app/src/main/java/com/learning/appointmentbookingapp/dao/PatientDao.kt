package com.learning.appointmentbookingapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.learning.appointmentbookingapp.entity.Patient

/*
This class will manage all the CRUD operations related to Patient
*/
class PatientDao(private val context: Context) {

    private val dbHelper = DbHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addPatient(patient: Patient): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", patient.name)
            put("gender", patient.gender)
            put("mobileNo", patient.mobileNo)
        }
        return db.insert("patient", null, contentValues)
    }

    fun getAllPatient(): ArrayList<Patient> {
        val patientList = ArrayList<Patient>()
        val cursor: Cursor = db.query("patient", null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val patientId = cursor.getLong(0)
            val patientName = cursor.getString(1)
            val gender = cursor.getString(2)
            val mobNo = cursor.getString(3)
            val patient = Patient(patientId, patientName, gender, mobNo)
            patientList.add(patient)
        }
        return patientList
    }

    fun deletePatient(patientId: Long): Boolean {
        val numOfRowsDeleted = db.delete("patient", "patientId=$patientId", null)
        return numOfRowsDeleted == 1
    }

    fun updatePatient(patient: Patient): Boolean {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", patient.name)
            put("gender", patient.gender)
            put("mobileNo", patient.mobileNo)
        }
        val numOfRowUpdated =
            db.update("patient", contentValues, "patientId=${patient.patientId}", null)
        return numOfRowUpdated == 1
    }
}