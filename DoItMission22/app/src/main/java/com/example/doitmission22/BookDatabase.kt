package com.example.doitmission22

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "book.db"
const val TABLE_NAME = "BOOK_INFO"
const val DATABASE_VERSION = 1

class BookInfo(var title: String, var author: String, var contents: String)
class BookDatabase private constructor(private val context: Context) {
    companion object {
        private var instance: BookDatabase? = null
        fun getInstance(context: Context): BookDatabase =
            instance ?: synchronized(this) {
                instance ?: BookDatabase(context).also { instance = it }
            }
    }
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase

    fun open(): Boolean {
        dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase

        return true
    }

    fun close() {
        db.close()
        instance = null
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            db?.execSQL("CREATE TABLE $TABLE_NAME (" +
                    " _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    " title TEXT, " +
                    " author TEXT, " +
                    " contents TEXT, " +
                    " CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                    ")")

            insertRecord(db!!, "Do it! 안드로이드 앱 프로그래밍", "정재곤", "안드로이드 기본서로 이지스퍼블리싱 출판사에서 출판했습니다.");
            insertRecord(db, "Programming Android", "Mednieks, Zigurd", "Oreilly Associates Inc에서 2011년 04월에 출판했습니다.");
            insertRecord(db, "센차터치 모바일 프로그래밍", "이병옥,최성민 공저", "에이콘출판사에서 2011년 10월에 출판했습니다.");
            insertRecord(db, "시작하세요! 안드로이드 게임 프로그래밍", "마리오 제흐너 저", "위키북스에서 2011년 09월에 출판했습니다.");
            insertRecord(db, "실전! 안드로이드 시스템 프로그래밍 완전정복", "박선호,오영환 공저", "DW Wave에서 2010년 10월에 출판했습니다.");
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

        private fun insertRecord(db: SQLiteDatabase, title: String, author: String, contents: String) {
            db.execSQL("INSERT INTO $TABLE_NAME (title, author, contents) values ( '$title', '$author', '$contents' );")
        }
    }

    fun insertRecord(title: String, author: String, contents: String) {
        db.execSQL("INSERT INTO $TABLE_NAME (title, author, contents) values ( '$title', '$author', '$contents' );")
    }

    fun selectAll(): ArrayList<BookInfo> {
        val result = ArrayList<BookInfo>()
        val cursor = db.rawQuery("SELECT title, author, contents from $TABLE_NAME", null)
        for (i in 0 until cursor.count) {
            cursor.moveToNext()
            val title = cursor.getString(0)
            val author = cursor.getString(1)
            val contents = cursor.getString(2)

            result.add(BookInfo(title, author, contents))
        }

        return result
    }
}