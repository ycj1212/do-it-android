package com.example.samplefragment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ImageSelectionCallback {
    var listFragment: ListFragment? = null
    var viewerFragment: ViewerFragment? = null

    val images = arrayOf(R.drawable.dream01, R.drawable.dream02, R.drawable.dream03)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        listFragment = manager.findFragmentById(R.id.listFragment) as ListFragment
        viewerFragment = manager.findFragmentById(R.id.viewerFragment) as ViewerFragment
    }

    override fun onImageSelected(position: Int) {
        viewerFragment?.setImage(images[position])
    }
}
