package com.learning.appointmentbookingapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.learning.appointmentbookingapp.Constant.DB_NAME
import com.learning.appointmentbookingapp.Constant.DB_VERSION

class DbHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    /*
    it will call only once in appLifeCycle*/
    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}