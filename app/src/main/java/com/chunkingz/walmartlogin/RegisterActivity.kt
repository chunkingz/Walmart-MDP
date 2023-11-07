package com.chunkingz.walmartlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.chunkingz.walmartlogin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccount()
    }

    private fun createAccount(){
        binding.createAccountBtn.setOnClickListener{
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || !isValidEmail(email)){
                Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                val newUserAccount = User(firstName, lastName, email, password)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("newUser", newUserAccount)
                startActivity(intent)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
