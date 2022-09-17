package com.yologger.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import java.lang.IllegalArgumentException

class ProductContentProvider : ContentProvider() {

    val AUTHORITY = "com.yologger.sqlite.ProductContentProvider"
    val TABLE_PRODUCTS = "products"
    val CONTENT_URI  = Uri.parse("content://$AUTHORITY/$TABLE_PRODUCTS")

    val URL_MATCH_RESULT_PRODUCT = 1
    val URL_MATCH_RESULT_PRODUCT_ID = 2

    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, TABLE_PRODUCTS, URL_MATCH_RESULT_PRODUCT)
        uriMatcher.addURI(AUTHORITY, "$TABLE_PRODUCTS/#", URL_MATCH_RESULT_PRODUCT_ID)
    }

    lateinit var dbHelper: DBHelper
    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {

        dbHelper = DBHelper(context!!, null, null, 1)
        db = dbHelper.writableDatabase

        return false
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var uriType = uriMatcher.match(uri)

        var id: Long = 0
        when(uriType) {
            URL_MATCH_RESULT_PRODUCT -> {
                id = db.insert(DBHelper.TABLE_PRODUCTS, null, values)
            }
            else -> {
                throw IllegalArgumentException("Unknown URI: ${uri}")
            }
        }
        requireContext().contentResolver.notifyChange(uri, null)
        return Uri.parse("TABLE_PRODUCTS/${id}")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        var uriType = uriMatcher.match(uri)

        when (uriType) {
            URL_MATCH_RESULT_PRODUCT -> {
                val query = "SELECT * FROM ${DBHelper.TABLE_PRODUCTS}"
                var cursor = db.rawQuery(query, null)
                return cursor
            }
            else -> {
                throw IllegalArgumentException("Unknown URI")
            }
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        var uriType = uriMatcher.match(uri)
        var db = dbHelper.writableDatabase
        var rowsUpdated = 0

        when(uriType) {
            URL_MATCH_RESULT_PRODUCT -> {
                rowsUpdated = db.update(DBHelper.TABLE_PRODUCTS, values,  selection, selectionArgs)
            }
            URL_MATCH_RESULT_PRODUCT_ID -> {
                var id = uri.lastPathSegment
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(DBHelper.TABLE_PRODUCTS, values,  "DBHelper.COLUMN_ID = $id", null)
                } else {
                    rowsUpdated = db.update(DBHelper.TABLE_PRODUCTS, values,  "DBHelper.COLUMN_ID = $id and $selection", selectionArgs)
                }
            }
            else -> {
                throw IllegalArgumentException("Unknown URI: ${uri}")
            }
        }

        requireContext().contentResolver.notifyChange(uri, null)
        return rowsUpdated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var uriType = uriMatcher.match(uri)
        var db = dbHelper.writableDatabase

        var rowsDeleted = 0

        when(uriType) {
            URL_MATCH_RESULT_PRODUCT -> {
                rowsDeleted = db.delete(DBHelper.TABLE_PRODUCTS, selection,  selectionArgs)
            }
            URL_MATCH_RESULT_PRODUCT_ID -> {
                var id = uri.lastPathSegment
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(DBHelper.TABLE_PRODUCTS, "${DBHelper.COLUMN_ID} = $id",  null)
                } else {
                    rowsDeleted = db.delete(DBHelper.TABLE_PRODUCTS, "${DBHelper.COLUMN_ID} = $id and $selection",  selectionArgs)
                }
            }
            else -> {
                throw IllegalArgumentException("Unknown URI: ${uri}")
            }
        }

        requireContext().contentResolver.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}