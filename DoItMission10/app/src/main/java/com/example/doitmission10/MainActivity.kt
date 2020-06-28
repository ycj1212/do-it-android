package com.example.doitmission10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.addDrawerListener(toggle)
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

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    pager.currentItem = 0
                    true
                }
                R.id.menu2 -> {
                    pager.currentItem = 1
                    true
                }
                R.id.menu3 -> {
                    pager.currentItem = 2
                    true
                }
                else -> true
            }
            drawerLayout?.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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
