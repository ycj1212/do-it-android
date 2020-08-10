package com.example.sampleprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class PersonProvider : ContentProvider() {
    companion object {
        private const val AUTHORITY = "com.example.provider"
        private const val BASE_PATH = "person"
        val CONTENT_URI: Uri =
            Uri.parse("content://$AUTHORITY/$BASE_PATH")
        private const val PERSONS = 1
        private const val PERSON_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(
                AUTHORITY,
                BASE_PATH,
                PERSONS
            )
            uriMatcher.addURI(
                AUTHORITY,
                "$BASE_PATH/#",
                PERSON_ID
            )
        }
    }

    private var database: SQLiteDatabase? = null

    override fun onCreate(): Boolean {
        val helper = DatabaseHelper(context!!)
        database = helper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        strings: Array<String>?,
        s: String?,
        strings1: Array<String>?,
        s1: String?
    ): Cursor? {
        val cursor: Cursor = when (uriMatcher.match(uri)) {
            PERSONS -> database!!.query(
                DatabaseHelper.TABLE_NAME, DatabaseHelper.ALL_COLUMNS,
                s, null, null, null, DatabaseHelper.PERSON_NAME + " ASC"
            )
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            PERSONS -> "vnd.android.cursor.dir/persons"
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
    }

    override fun insert(
        uri: Uri,
        contentValues: ContentValues?
    ): Uri? {
        val id = database!!.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
        if (id > 0) {
            val _uri =
                ContentUris.withAppendedId(CONTENT_URI, id)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("추가 실패 -> URI :$uri")
    }

    override fun delete(
        uri: Uri,
        s: String?,
        strings: Array<String>?
    ): Int {
        var count = 0
        count = when (uriMatcher.match(uri)) {
            PERSONS -> database!!.delete(DatabaseHelper.TABLE_NAME, s, strings)
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        s: String?,
        strings: Array<String>?
    ): Int {
        var count = 0
        count = when (uriMatcher.match(uri)) {
            PERSONS -> database!!.update(DatabaseHelper.TABLE_NAME, contentValues, s, strings)
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }
}