package com.example.taxiapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val okButton = findViewById<Button>(R.id.okButton)
        val fromStreetEditText = findViewById<EditText>(R.id.fromStreetEditText)
        val fromHouseEditText = findViewById<EditText>(R.id.fromHouseEditText)
        val fromFlatEditText = findViewById<EditText>(R.id.fromFlatEditText)
        val toStreetEditText = findViewById<EditText>(R.id.toStreetEditText)
        val toHouseEditText = findViewById<EditText>(R.id.toHouseEditText)
        val toFlatEditText = findViewById<EditText>(R.id.toFlatEditText)

        okButton.setOnClickListener {
            val fromStreet = fromStreetEditText.text.toString()
            val fromHouse = fromHouseEditText.text.toString()
            val fromFlat = fromFlatEditText.text.toString()
            val toStreet = toStreetEditText.text.toString()
            val toHouse = toHouseEditText.text.toString()
            val toFlat = toFlatEditText.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("fromStreet", fromStreet)
            resultIntent.putExtra("fromHouse", fromHouse)
            resultIntent.putExtra("fromFlat", fromFlat)
            resultIntent.putExtra("toStreet", toStreet)
            resultIntent.putExtra("toHouse", toHouse)
            resultIntent.putExtra("toFlat", toFlat)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
