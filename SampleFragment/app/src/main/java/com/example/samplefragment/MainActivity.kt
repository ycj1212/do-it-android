package com.example.samplefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var mainFragment: MainFragment? = null
    var menuFragment: MenuFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as? MainFragment?
        menuFragment = MenuFragment()
    }

    public fun onFragmentChanged(index: Int) {
        if (index == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.container, menuFragment!!).commit()
        } else if (index == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment!!).commit()
        }
    }
}
