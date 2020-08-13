package com.example.doitmission22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val fragment1 = Fragment1()
        val fragment2 = Fragment2()

        viewPagerAdapter.addItem(fragment1)
        viewPagerAdapter.addItem(fragment2)
        viewPager.adapter = viewPagerAdapter

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.setupWithViewPager(viewPager)
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val items = ArrayList<Fragment>()
        private val titles = arrayOf("입력", "조회")

        fun addItem(item: Fragment) { items.add(item) }
        override fun getItem(position: Int): Fragment = items[position]
        override fun getCount(): Int = items.size
        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}
