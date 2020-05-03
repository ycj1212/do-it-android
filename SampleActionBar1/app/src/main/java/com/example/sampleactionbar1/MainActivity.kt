package com.example.sampleactionbar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar

var abar: ActionBar? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        abar = supportActionBar

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            abar!!.setLogo(R.drawable.home)
            abar!!.displayOptions = ActionBar.DISPLAY_USE_LOGO
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val curId = item.itemId
        when (curId) {
            R.id.menu_refresh -> Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
            R.id.menu_search -> Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
            R.id.menu_settings -> Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
