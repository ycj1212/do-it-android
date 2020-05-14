package com.example.sampletab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var fragment1: Fragment1? = null
    var fragment2: Fragment2? = null
    var fragment3: Fragment3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1!!).commit()

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("통화기록"))
        tabs.addTab(tabs.newTab().setText("스팸기록"))
        tabs.addTab(tabs.newTab().setText("연락처"))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                Log.d("MainActivity", "선택된 탭 : ${position}")

                var selected: Fragment? = null
                when (position) {
                    0 -> selected = fragment1
                    1 -> selected = fragment2
                    2 -> selected = fragment3
                }

                supportFragmentManager.beginTransaction().replace(R.id.container, selected!!).commit()
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }
}
