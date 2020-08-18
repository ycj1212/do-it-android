package com.example.doitmission22

interface OnDatabaseCallback {
    fun insert(title: String, author: String, contents: String)
    fun selectAll(): ArrayList<BookInfo>
}