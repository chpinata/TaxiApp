package com.example.taxiapp
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Получаем доступ к Views
        val editTextPhoneNumber = findViewById<EditText>(R.id.editTextPhoneNumber)
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val buttonRegistration = findViewById<Button>(R.id.buttonRegistration)

        // Проверяем, сохранены ли регистрационные данные
        val savedPhoneNumber = sharedPreferences.getString("phone_number", "")
        if (savedPhoneNumber != "") {
            editTextPhoneNumber.setText(savedPhoneNumber)
            editTextFirstName.setText(sharedPreferences.getString("first_name", ""))
            editTextLastName.setText(sharedPreferences.getString("last_name", ""))
            buttonRegistration.text = "Log in"
        }

        // Обработчик нажатия кнопки "Registration" или "Log in"
        buttonRegistration.setOnClickListener {
            // Получаем данные из EditText
            val phoneNumber = editTextPhoneNumber.text.toString()
            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()

            // Сохраняем данные в SharedPreferences
            with(sharedPreferences.edit()) {
                putString("phone_number", phoneNumber)
                putString("first_name", firstName)
                putString("last_name", lastName)
                apply()
            }

            // Создаем Intent для запуска второй Activity
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("phone_number", phoneNumber)
            intent.putExtra("first_name", firstName)
            intent.putExtra("last_name", lastName)
            startActivity(intent)
        }
    }
}
