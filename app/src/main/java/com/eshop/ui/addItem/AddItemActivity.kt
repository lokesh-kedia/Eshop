package com.eshop.ui.addItem

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.eshop.BuildConfig
import com.eshop.MainActivity
import com.eshop.R
import com.eshop.ui.roomDB.db_Items.DbItems
import com.eshop.ui.roomDB.db_Items.DbItemsModel
import kotlinx.android.synthetic.main.activity_add_item.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {
    val REQUEST_TAKE_PHOTO = 1
    val REQUEST_IMAGE_CAPTURE = 1
    val MY_PERMISSIONS_REQUEST_CODE = 8989
    lateinit var currentPhotoPath: String
    var photoURI: Uri? = null
    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.InternalTheme)
        setContentView(R.layout.activity_add_item)
        setUpToolbar(this, toolbar)
    }

    companion object {
        fun setUpToolbar(context: Activity, toolbar: View) {
            (context as AppCompatActivity).setSupportActionBar(toolbar as Toolbar)
            val actionBar = context.supportActionBar
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_black_24dp)
                actionBar.setDisplayHomeAsUpEnabled(true)
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onSkipClicked(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
    fun onAddImageClicked(view: View) {
        dispatchTakePictureIntent()
    }

    fun onAddMoreClicked(view: View) {
        if (checkValidation()) {
            saveToDB()
            recreate()
        } else {
            Toast.makeText(this, "All the fields are required", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveToDB() {
        val dbItemModel = ViewModelProvider(this).get(DbItemsModel::class.java)
        val stream = ByteArrayOutputStream()
        bitmap?.compress(
            Bitmap.CompressFormat.WEBP,
            2,
            stream
        )
        val byteArray = stream.toByteArray()

        val dbItems = DbItems(
            item_name_text.text.toString(),
            item_category_text.text.toString(),
            item_price_text.text.toString().toInt(),
            item_quantity_text.text.toString(),
            item_discount_text.text.toString().toInt(),
            byteArray
        )
        dbItemModel.insert(dbItems)
    }

    private fun checkValidation(): Boolean {
        if (item_name_text.text.isNullOrBlank() || item_category_text.text.isNullOrBlank() || item_price_text.text.isNullOrBlank() || item_quantity_text.text.isNullOrBlank() || item_discount_text.text.isNullOrBlank() || bitmap == null) {
            return false
        }

        return true
    }

    private fun dispatchTakePictureIntent() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("MapsTest", "Permission: ")
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), MY_PERMISSIONS_REQUEST_CODE
            )

            return
        }
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        this,
                        "${BuildConfig.APPLICATION_ID}.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }


    }

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            try {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, photoURI)
                } else {
                    photoURI?.let {
                        val source = ImageDecoder.createSource(
                            contentResolver,
                            it
                        )
                        bitmap = ImageDecoder.decodeBitmap(source)
                    }
                }
                bitmap?.let {
                    item_image.setImageBitmap(bitmap)
                }

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

    }


}