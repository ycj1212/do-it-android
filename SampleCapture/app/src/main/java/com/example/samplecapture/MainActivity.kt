package com.example.samplecapture

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {
    lateinit var cameraView: CameraSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val previewFrame: FrameLayout = findViewById(R.id.previewFrame)
        cameraView = CameraSurfaceView(this)
        previewFrame.addView(cameraView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            takePicture()
        }

        AutoPermissions.Companion.loadAllPermissions(this, 101)
    }

    private fun takePicture() {
        cameraView.capture(Camera.PictureCallback { data, camera ->
            val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            val outUriStr = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Captured Image", "Captured Image using Camera.")  // 전달받은 바이트 배열을 Bitmap 객체로 만들기
            if (outUriStr == null) {
                return@PictureCallback
            } else {
                val outUri = Uri.parse(outUriStr)
                sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri))
            }
            camera.startPreview()
        })
    }

    inner class CameraSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
        lateinit var mHolder: SurfaceHolder
        var camera: Camera? = null

        init {
            mHolder = holder
            mHolder.addCallback(this)
        }

        // 서피스뷰가 만들어질 때 카메라 객체를 참조한 후 미리보기 화면으로 홀더 객체 설정
        override fun surfaceCreated(holder: SurfaceHolder?) {
            camera = Camera.open()
            setCameraOrientation()
            camera?.setPreviewDisplay(mHolder)
        }

        // 서비스뷰의 화면 크기가 바뀌는 등의 변경 시점에 미리보기 시작
        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            camera?.startPreview()
        }

        // 서피스뷰가 없어질 때 미리보기 중지
        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            camera?.stopPreview()
            camera?.release()
            camera = null
        }

        fun capture(handler: Camera.PictureCallback): Boolean {
            if (camera != null) {
                camera?.takePicture(null, null, handler)
                return true
            } else {
                return false
            }
        }

        fun setCameraOrientation() {
            if (camera == null) {
                return
            }

            val info = Camera.CameraInfo()
            Camera.getCameraInfo(0, info)

            val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val rotation = manager.defaultDisplay.rotation  // 회전에 대한 정보 확인
            var degrees = 0
            when (rotation) {
                Surface.ROTATION_0 -> degrees = 0
                Surface.ROTATION_90 -> degrees = 90
                Surface.ROTATION_180 -> degrees = 180
                Surface.ROTATION_270 -> degrees = 270
            }

            var result = 0
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = (info.orientation + degrees) % 360
                result = (360 - result) % 360
            } else {
                result = (info.orientation - degrees + 360) % 360
            }

            camera?.setDisplayOrientation(result)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions as Array<String>, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions denied : ${permissions.size}", Toast.LENGTH_LONG).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(this, "permissions granted : ${permissions.size}", Toast.LENGTH_LONG).show()
    }
}
