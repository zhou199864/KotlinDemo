package com.example.myapplication.kotlindemo.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.example.myapplication.kotlindemo.ActivityCollector
import com.example.myapplication.kotlindemo.R
import kotlinx.android.synthetic.main.activity_camera_ablum.*
import java.io.File
/**
* @Author Zyique Zhou
* @Date 7/14/2020
* @Description 调用系统摄像头拍照
*/
class CameraAlbumActivity : AppCompatActivity() {

    private val takePhoto = 1
    private val fromAlbum = 2
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_ablum)
        ActivityCollector.addActivity(this)
        btn_take_photo.setOnClickListener {
            outputImage = File(externalCacheDir,"output_img.jpg")
            if (outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                //manifest中需要注册
                FileProvider.getUriForFile(this,"com.example.myapplication.kotlindemo.ui.CameraAlbumActivity.fileprovider",outputImage)
            } else {
                Uri.fromFile(outputImage)
            }
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,takePhoto)
        }

        btn_from_album.setOnClickListener {
            //打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        "requestCode $requestCode"
        when (requestCode){
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK){
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    img_take.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
            fromAlbum -> {
                if (resultCode == Activity.RESULT_OK && data != null){
                    data.data?.let {
                        val bitmap = getBitmapFromUri(it)
                        img_take.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri,"r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun rotateIfRequired(bitmap: Bitmap): Bitmap{
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when (orientation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitMap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitMap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitMap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitMap(bitmap: Bitmap, degree: Int): Bitmap{
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        //回收Bitmap对象
        bitmap.recycle()
        return rotatedBitmap
    }

    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context,CameraAlbumActivity::class.java)
            context.startActivity(intent)
        }
    }
}