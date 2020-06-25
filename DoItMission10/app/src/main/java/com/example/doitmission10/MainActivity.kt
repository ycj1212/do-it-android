package com.example.doitmission10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1: Fragment = Fragment1()
        val fragment2: Fragment = Fragment2()
        val fragment3: Fragment = Fragment3()

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab1 -> {
                    Toast.makeText(this, "tab1", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()
                    true
                }
                R.id.tab2 -> {
                    Toast.makeText(this, "tab2", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment2).commit()
                    true
                }
                R.id.tab3 -> {
                    Toast.makeText(this, "tab3", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment3).commit()
                    true
                }
            }

            false
        }
    }
}
