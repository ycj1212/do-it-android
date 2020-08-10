package com.example.sampledatabase2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

const val NAME = "employee.db"
const val VERSION = 1

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    // 데이터베이스가 생성될 때
    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate 호출됨")
        val sql = "create table if not exists emp(" +
                " _id integer PRIMARY KEY autoincrement, " +
                " name text, " +
                " age integer, " +
                " mobile text)"

        db?.execSQL(sql)
    }

    // 데이터베이스를 열 때
    override fun onOpen(db: SQLiteDatabase?) {
        println("onOpen 호출됨")
    }

    // 데이터베이스를 업그레이드 할 때
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onUpgrade 호출됨: $oldVersion -> $newVersion")

        if (newVersion > 1) {
            db?.execSQL("DROP TABLE IF EXISTS emp")
        }
    }

    private fun println(data: String) {
        Log.d("DatabaseHelper", data)
    }
}