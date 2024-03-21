package com.example.taxiapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    private val REQUEST_CODE_SET_PATH = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Получаем данные о пользователе из Intent
        val phoneNumber = intent.getStringExtra("phone_number")
        val firstName = intent.getStringExtra("first_name")
        val lastName = intent.getStringExtra("last_name")

        val textViewPhoneNumber = findViewById<TextView>(R.id.textViewPhoneNumber)
        val textViewFirstName = findViewById<TextView>(R.id.textViewFirstName)
        val textViewLastName = findViewById<TextView>(R.id.textViewLastName)
        textViewPhoneNumber.text = "Phone Number: $phoneNumber"
        textViewFirstName.text = "First Name: $firstName"
        textViewLastName.text = "Last Name: $lastName"

        // Обработчик нажатия кнопки "Set Path"
        val buttonSetPath = findViewById<Button>(R.id.buttonSetPath)
        buttonSetPath.setOnClickListener {
            // Создаем Intent для вызова третьей активности
            val intent = Intent(this, ThirdActivity::class.java)
            // Запускаем третью активность с ожиданием результата
            startActivityForResult(intent, REQUEST_CODE_SET_PATH)
        }

        // Тост при нажатия кнопки "Call Taxi"
        val buttonCallTaxi = findViewById<Button>(R.id.buttonCallTaxi)
        buttonCallTaxi.setOnClickListener {
            Toast.makeText(this, "Taxi called successfully", Toast.LENGTH_SHORT).show()
        }
    }

    // Обработка результата, возвращаемого третьей активностью
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SET_PATH && resultCode == Activity.RESULT_OK) {
            val route = data?.getStringExtra("route")
            val textViewRoute = findViewById<TextView>(R.id.textViewRoute)
            textViewRoute.text = "Route: $route"
            val buttonCallTaxi = findViewById<Button>(R.id.buttonCallTaxi)
            buttonCallTaxi.isEnabled = true
        }
    }
}
