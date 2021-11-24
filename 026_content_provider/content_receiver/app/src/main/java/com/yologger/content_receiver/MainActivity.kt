package com.yologger.content_receiver

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        const val REQUEST_CODE_PERMISSIONS = 1
    }


    private val requiredPermissions =
        arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)

    val buttonInsert: Button by lazy { findViewById<Button>(R.id.button_insert) }
//    val buttonUpdate: Button by lazy { findViewById<Button>(R.id.button_update) }
//    val buttonQuery: Button by lazy { findViewById<Button>(R.id.button_query) }
//    val buttonDelete: Button by lazy { findViewById<Button>(R.id.button_insert) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonInsert.setOnClickListener {

            val AUTHORITY = "com.yologger.sqlite.ProductContentProvider"
            val TABLE_PRODUCTS = "products"
            val CONTENT_URI  = Uri.parse("content://${AUTHORITY}/${TABLE_PRODUCTS}")

            var cursor = contentResolver.query(CONTENT_URI, null, null, null, null)

            Toast.makeText(this, "Count: ${cursor!!.count}", Toast.LENGTH_SHORT).show()
        }
    }
}