package com.learning.appointmentbookingapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.learning.appointmentbookingapp.Constant
import com.learning.appointmentbookingapp.Constant.DB_NAME

class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            p0?.execSQL(Constant.CREATE_DOCTOR_TABLE)
            p0?.execSQL(Constant.CREATE_PATIENT_TABLE)
            p0?.execSQL(Constant.CREATE_APPOINTMENT_TABLE)
        } catch (dbe: SQLiteException) {
            dbe.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 2 && newVersion == 3) {
            db?.execSQL(Constant.CREATE_APPOINTMENT_TABLE)
            //   db?.execSQL(CREATE_PATIENT_TABLE)
        }
        /*       db?.execSQL("DROP Table if exists doctor")
               db?.execSQL("DROP Table if exists patient")
               //db?.execSQL("DROP Table if exists appointment")*/
    }
}