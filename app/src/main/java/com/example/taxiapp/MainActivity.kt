package com.example.taxiapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val firstNameEditText = findViewById<EditText>(R.id.firstNameEditText)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEditText)
        val registrationButton = findViewById<Button>(R.id.registrationButton)

        // Проверяем, сохранены ли регистрационные данные
        val savedPhoneNumber = sharedPreferences.getString("phone_number", "")
        if (savedPhoneNumber != "") {
            phoneEditText.setText(savedPhoneNumber)
            firstNameEditText.setText(sharedPreferences.getString("first_name", ""))
            lastNameEditText.setText(sharedPreferences.getString("last_name", ""))
            registrationButton.text = "Log in"
        }

        // Настройка поля ввода для номера телефона
        phoneEditText.filters = arrayOf(InputFilter.LengthFilter(12), InputFilter { source, _, _, _, _, _ ->
            // Проверка на ввод только чисел
            if (source.toString().matches("[0-9]+".toRegex())) null else ""
        })

        // Обработчик нажатия кнопки "Registration" или "Log in"
        registrationButton.setOnClickListener {
            val phoneNumber = phoneEditText.text.toString()
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()

            // Проверка на пустые поля
            if (phoneNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Сохраняем данные в SharedPreferences
            with(sharedPreferences.edit()) {
                putString("phone_number", phoneNumber)
                putString("first_name", firstName)
                putString("last_name", lastName)
                apply()
            }

            // Создаем Intent для запуска второго Activity
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("phone_number", phoneNumber)
            intent.putExtra("first_name", firstName)
            intent.putExtra("last_name", lastName)
            startActivity(intent)
        }
    }
}
