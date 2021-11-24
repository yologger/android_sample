package com.yologger.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper constructor(
    context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int
) : SQLiteOpenHelper(
    context, DATABASE_NAME, factory, DATABASE_VERSION
) {

    companion object {
        const val DATABASE_NAME = "productDB.db"
        const val DATABASE_VERSION = 1
        const val TABLE_PRODUCTS = "products"

        const val COLUMN_ID = "id"
        const val COLUMN_PRODUCT_NAME = "product_name"
        const val COLUMN_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE ${TABLE_PRODUCTS} " +
                "(" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_PRODUCT_NAME TEXT," +
                "$COLUMN_QUANTITY INTEGER" +
                ")"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS ${TABLE_PRODUCTS}"
        db?.execSQL(query)
        onCreate(db)
    }

    fun addProduct(product: Product) {
        val values = ContentValues()

        values.put(COLUMN_PRODUCT_NAME, product._productName)
        values.put(COLUMN_QUANTITY, product._quantity)

        var db = writableDatabase
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()
    }

    fun findProduct(productName: String): Product? {
        val query = "SELECT * FROM ${TABLE_PRODUCTS} WHERE ${COLUMN_PRODUCT_NAME} = \"${productName}\""
        val db = writableDatabase
        var cursor = db.rawQuery(query, null)

        var product: Product? = null

        if (cursor.moveToFirst()) {
           product = Product(
               _id = Integer.parseInt(cursor.getString(0)),
               _productName = cursor.getString(1),
               _quantity =  Integer.parseInt(cursor.getString(2))
           )
            cursor.close()
        } else {
            product = null
        }

        db.close()
        return product
    }

    fun deleteProduct(productName: String): Boolean {
        var result = false

        val query = "SELECT * FROM ${TABLE_PRODUCTS} WHERE ${COLUMN_PRODUCT_NAME} = \"${productName}\""

        val db = writableDatabase

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val productId = Integer.parseInt(cursor.getString(0))
            Log.d("TEST", "productId: ${productId}")
            db.delete(TABLE_PRODUCTS, "$COLUMN_ID = ${productId}", null)
            cursor.close()
            result = true
        }

        db.close()
        return result
    }
}