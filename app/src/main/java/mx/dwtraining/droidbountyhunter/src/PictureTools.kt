package mx.dwtraining.droidbountyhunter.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PictureTools {

    private var context: Context? = null

    companion object {
        val MEDIA_TYPE_IMAGE = 1
        val REQUEST_CODE = 1707

        private val TAG = PictureTools::class.java.simpleName
        private var BASE_PATH = ""
        private var instance: PictureTools? = null

        var currentPhotoPath = ""

        private fun getInstance(): PictureTools? {
            if (instance == null) {
                synchronized(PictureTools){
                    if (instance == null) {
                        instance = PictureTools()
                    }
                }
            }
            return instance
        }

        /** Create a file Uri for saving an image or video  */
        fun getOutputMediaFileUri(context: Context, type: Int): Uri? {
            getInstance()!!.context = context
            return try {
                FileProvider.getUriForFile(instance!!.context!!,
                    instance!!.context!!.packageName + ".provider", outputMediaFile)
            } catch (e: IOException) {
                null
            }
        }

        /** Create a File for saving an image or video  */
        private val outputMediaFile: File
            @Throws(IOException::class)
            get() {
                val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).path, "DroidBountyHunterPictures")

                BASE_PATH = mediaStorageDir.path + File.separator // "/"
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.d("File", "No se pudo crear el folder")
                    }
                }
                val timeStamp = SimpleDateFormat("yyyy-MM-dd_HHmmss").format(Date())
                var path = "DBH_$timeStamp.png"
                path = path.replace(" ", "_")
                if (path.contains("'")) {
                    path = path.replace("'", "")
                }
                val imageFileName = BASE_PATH + path
                val image = File(imageFileName)
                currentPhotoPath = image.absolutePath

                return image
            }
    }
}