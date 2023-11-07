package com.chunkingz.walmartlogin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.chunkingz.walmartlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createStaticUsers()
        signInBtnClicked()
        registerBtnClicked()
        forgotPasswordClicked()

        if (intent.hasExtra("newUser")){
            val newUserAccount = intent.getSerializableExtra("newUser") as User
            newUserAccount.let {
                users.add(newUserAccount)
            }
        }
    }

    private fun createStaticUsers(){
        users.add(User("Fortune", "King", "chun@mail.com", "1234"))
        users.add(User("Bret", "Graham", "bret@mail.com", "1234"))
        users.add(User("Ervin", "Howell", "ervin@mail.com", "1234"))
        users.add(User("Samantha", "Bayer", "sam@mail.com", "1234"))
        users.add(User("Patricia", "Dwight", "patricia@mail.com", "1234"))
    }

    private fun signInBtnClicked(){
        binding.signInBtn.setOnClickListener{
            val email = binding.emailAddress.text.toString().lowercase()
            val password = binding.password.text.toString().lowercase()
            var isValidLogin = false

            for (user in users){
                if (user.userName == email && user.password == password) {
                    isValidLogin = true
                    Toast.makeText(this, "log in successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ShoppingActivity::class.java)
                    intent.putExtra("username", user.userName)
                    startActivity(intent)
                }
            }
            if (!isValidLogin) Toast.makeText(this, "invalid credentials, try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerBtnClicked(){
        binding.registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun forgotPasswordClicked(){
        binding.forgotPassword.setOnClickListener{
            if (binding.emailAddress.text.isEmpty()){
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(binding.emailAddress.text.toString())){
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            } else {
                var userFound = false
                for (user in users){
                    if(user.userName == binding.emailAddress.text.toString().lowercase()){
                        userFound = true
                        sendPasswordToEmail(user.firstName, user.userName, user.password)
                    }
                }
                if (!userFound){
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendPasswordToEmail(firstName: String, username: String, password: String) {
        val emailSubject = "Your Password"
        val emailText = "Dear $firstName, your password is: $password"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$username")
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
        intent.putExtra(Intent.EXTRA_TEXT, emailText)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show()
        }
    }

}
