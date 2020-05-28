package com.example.samplereceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AutoPermissions.Companion.loadAllPermissions(this, 101) // 모든 위험 권한을 자동 부여하도록 하는 메서드 호출
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        AutoPermissions.Companion.parsePermissions(this, requestCode,
            permissions as Array<String>, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions denied: ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions granted: ${permissions.size}", Toast.LENGTH_LONG).show()
    }
}
