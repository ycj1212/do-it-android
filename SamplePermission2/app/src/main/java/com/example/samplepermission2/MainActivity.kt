package com.example.samplepermission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AutoPermissions.loadAllPermissions(this, 101)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions as Array<String>, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions denied: ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions granted: ${permissions.size}", Toast.LENGTH_LONG).show()
    }
}
