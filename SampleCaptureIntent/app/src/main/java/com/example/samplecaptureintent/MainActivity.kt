package com.example.samplecaptureintent

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener
import java.io.File

class MainActivity : AppCompatActivity(), AutoPermissionsListener {
    lateinit var imageView: ImageView
    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            takePicture()
        }

        AutoPermissions.Companion.loadAllPermissions(this, 101)
    }

    private fun takePicture() {
        if (file == null) {
            file = createFile()
        }

        // File 객체로부터 Uri 객체 만들기
        val fileUri = FileProvider.getUriForFile(this, "com.example.samplecaptureintent.fileprovider", file!!)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 101) // 사진 찍기 화면 띄우기
        }
    }

    private fun createFile(): File {
        val fileName = "capture.jpg"
        val storageDir = Environment.getExternalStorageDirectory()
        val outFile = File(storageDir, fileName)

        return outFile
    }

    // 카메라 앱에서 찍은 사진을 파일에서 확인하여 이미지뷰에 설정
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == RESULT_OK) {
            val options: BitmapFactory.Options = BitmapFactory.Options()    // 이미지 파일을 Bitmap 객체로 만들기
            options.inSampleSize = 8    // 1/8 크기로 축소
            val bitmap = BitmapFactory.decodeFile(file?.absolutePath, options)

            imageView.setImageBitmap(bitmap)
        }
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
        Toast.makeText(this, "permissions denied : ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions granted : ${permissions.size}", Toast.LENGTH_LONG).show()
    }
}