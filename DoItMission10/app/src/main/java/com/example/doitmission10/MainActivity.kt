package com.example.doitmission10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val fragment1: Fragment = Fragment1()
        val fragment2: Fragment = Fragment2()
        val fragment3: Fragment = Fragment3()

        val pager: ViewPager = findViewById(R.id.pager)
        pager.offscreenPageLimit = 3

        val adapter: MyPagerAdapter = MyPagerAdapter(supportFragmentManager)
        adapter.addItem(fragment1)
        adapter.addItem(fragment2)
        adapter.addItem(fragment3)

        pager.adapter = adapter

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab1 -> {
                    Toast.makeText(this, "tab1", Toast.LENGTH_SHORT).show()
                    pager.currentItem = 0
                    //fm.beginTransaction().replace(R.id.container, fragment1).commit()
                    true
                }
                R.id.tab2 -> {
                    Toast.makeText(this, "tab2", Toast.LENGTH_SHORT).show()
                    pager.currentItem = 1
                    //fm.beginTransaction().replace(R.id.container, fragment2).commit()
                    true
                }
                R.id.tab3 -> {
                    Toast.makeText(this, "tab3", Toast.LENGTH_SHORT).show()
                    pager.currentItem = 2
                    //fm.beginTransaction().replace(R.id.container, fragment3).commit()
                    true
                }
            }

            false
        }
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val items = ArrayList<Fragment>()

        fun addItem(item: Fragment) {
            items.add(item)
        }

        override fun getItem(position: Int): Fragment {
            return items.get(position)
        }

        override fun getCount(): Int {
            return items.size
        }
    }
}
