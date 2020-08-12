package com.example.samplealbum

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            openGallery()
        }

        val permissions: Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) // 위험 권한 부여할 권한 지정
        val targetList: ArrayList<String> = ArrayList<String>()

        for (i in permissions.indices) {
            val curPermission = permissions[i]
            val permissionCheck = ContextCompat.checkSelfPermission(this, curPermission)

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "$curPermission 권한 있음.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "$curPermission 권한 없음.", Toast.LENGTH_LONG).show()
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, "$curPermission 권한 설명 필요함.", Toast.LENGTH_LONG).show()
                } else {
                    targetList.add(curPermission)
                }
            }
        }

        val targets: Array<String> = Array(targetList.size) { "" }
        targetList.toArray(targets)

        ActivityCompat.requestPermissions(this, targets, 101)   // 위험 권한 부여 요청
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = " image/* "
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                val fileUri = data?.data
                val resolver = contentResolver  // ContentResolver 객체 참조하기
                val instream = resolver.openInputStream(fileUri!!)  // ContentResolver 객체의 openInputStream() 메소드로 파일 읽어 들이기
                val imgBitmap = BitmapFactory.decodeStream(instream)
                imageView.setImageBitmap(imgBitmap)
                instream?.close()

                // Intent.getData(): Uri -> ContentResolver.openInputStream() -> BitmapFactory.decodeStream() -> ImageView.setImageBitmap()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                if (grantResults.size > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "첫 번째 권한을 사용자가 승인함.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "첫 번째 권한이 거부됨.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
