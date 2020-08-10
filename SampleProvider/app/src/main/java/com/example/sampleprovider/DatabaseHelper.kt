package com.example.sampleprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "person.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "person"
        const val PERSON_ID = "_id"
        const val PERSON_NAME = "name"
        const val PERSON_AGE = "age"
        const val PERSON_MOBILE = "mobile"
        val ALL_COLUMNS: Array<String> = arrayOf( PERSON_ID, PERSON_NAME, PERSON_AGE, PERSON_MOBILE )

        private const val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ( " +
                    "$PERSON_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$PERSON_NAME TEXT, " +
                    "$PERSON_AGE INTEGER, " +
                    "$PERSON_MOBILE TEXT )"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}