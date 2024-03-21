package com.example.taxiapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

            if (fromStreet.isNotBlank() && fromHouse.isNotBlank() && fromFlat.isNotBlank() &&
                toStreet.isNotBlank() && toHouse.isNotBlank() && toFlat.isNotBlank()) {
                val resultIntent = Intent().apply {
                    putExtra(
                        "route",
                        "Point From: $fromStreet, $fromHouse, $fromFlat\nPoint To: $toStreet, $toHouse, $toFlat"
                    )
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                // Если хотя бы одно поле не заполнено, выводим сообщение об ошибке
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
