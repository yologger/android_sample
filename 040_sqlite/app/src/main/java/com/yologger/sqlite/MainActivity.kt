package com.yologger.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val buttonAdd: Button by lazy { findViewById<Button>(R.id.activity_main_btn_add) }
    val buttonFind: Button by lazy { findViewById<Button>(R.id.activity_main_btn_find) }
    val buttonRemove: Button by lazy { findViewById<Button>(R.id.activity_main_btn_remove) }

    val editTextProductName: EditText by lazy { findViewById<EditText>(R.id.activity_main_tv_product_name) }
    val editTextQuantity: EditText by lazy { findViewById<EditText>(R.id.activity_main_tv_quantity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdd.setOnClickListener {
            val dbHelper = DBHelper(this, null, null, 1)
            var productName = editTextProductName.text.toString()
            val quantity = Integer.parseInt(editTextQuantity.text.toString())
            val product = Product(_productName = productName, _quantity = quantity)
            dbHelper.addProduct(product)
            Toast.makeText(this, "Added.", Toast.LENGTH_SHORT).show()
        }
        buttonFind.setOnClickListener {
            val dbHelper = DBHelper(this, null, null, 1)
            var productName = editTextProductName.text.toString()
            val product = dbHelper.findProduct(productName)
            if (product != null) {
                Toast.makeText(this, "${product.toString()}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Such Item.", Toast.LENGTH_SHORT).show()
            }

        }
        buttonRemove.setOnClickListener {
            val dbHelper = DBHelper(this, null, null, 1)
            var result = dbHelper.deleteProduct("iPhone")
            if (result) {
                Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Not Deleted. No Such Item.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}