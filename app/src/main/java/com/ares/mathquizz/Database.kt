package com.ares.mathquizz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database (context: Context) : SQLiteOpenHelper(context, "DB", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        // tạo bảng
        p0?.execSQL("create table users(userId integer primary key autoincrement, userName text, password text, score integer default (0))")

        // add data
        p0?.execSQL("insert into users(userName, password) values ('0123456789', '0000')")
        p0?.execSQL("insert into users(userName, password) values ('9876543210', '0000')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}