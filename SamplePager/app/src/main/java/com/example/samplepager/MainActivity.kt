package com.example.samplepager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    var pager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            pager?.currentItem = 1
        }

        pager = findViewById(R.id.pager)
        pager?.offscreenPageLimit = 3

        val adapter = MyPagerAdapter(supportFragmentManager)

        val fragment1 = Fragment1()
        adapter.addItem(fragment1)
        val fragment2 = Fragment2()
        adapter.addItem(fragment2)
        val fragment3 = Fragment3()
        adapter.addItem(fragment3)

        pager?.adapter = adapter
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val items = ArrayList<Fragment>()

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
