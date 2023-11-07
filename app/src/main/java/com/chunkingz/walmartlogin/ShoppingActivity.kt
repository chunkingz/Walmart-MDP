package com.chunkingz.walmartlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chunkingz.walmartlogin.databinding.ActivityShoppingBinding


class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loggedInUser = intent
        val user = loggedInUser.getStringExtra("username")
        binding.welcome.text = "welcome $user"

        userSelectedCategory()
    }

    private fun userSelectedCategory(){
        binding.electronics.setOnClickListener{
            displayToast("Electronics")
        }
        binding.clothing.setOnClickListener{
            displayToast("Clothing")
        }
        binding.beauty.setOnClickListener{
            displayToast("Beauty")
        }
        binding.food.setOnClickListener{
            displayToast("Food")
        }
    }

    private fun displayToast(category: String){
        Toast.makeText(this, "You have chosen the $category category of shopping", Toast.LENGTH_SHORT).show()
    }
}
