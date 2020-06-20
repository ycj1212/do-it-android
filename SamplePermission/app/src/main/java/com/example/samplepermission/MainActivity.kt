package com.example.samplepermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 위험 권한을 부여할 권한 지정하기
        val permissions: Array<String> = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        checkPermissions(permissions)
    }

    fun checkPermissions(permissions: Array<String>) {
        val targetList = ArrayList<String>()

        for (i in permissions.indices) {
            val curPermission = permissions[i]
            val permissionCheck = ContextCompat.checkSelfPermission(this, curPermission)
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "$curPermission 권한 있음", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "$curPermission 권한 없음", Toast.LENGTH_LONG).show()
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, "$curPermission 권한 설명 필요함", Toast.LENGTH_LONG).show()
                } else {
                    targetList.add(curPermission)
                }
            }
        }

        val targets = Array<String>(targetList.size) {""}
        targetList.toArray(targets)

        // 위험 권한 부여 요청하기
        ActivityCompat.requestPermissions(this, targets, 101)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫 번째 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "첫 번째 권한 거부됨", Toast.LENGTH_LONG).show()
                }

                return
            }
        }
    }
}
