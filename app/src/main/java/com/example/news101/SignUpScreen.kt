package com.example.news101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up_screen.*

class SignUpScreen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        auth = Firebase.auth

        button3.setOnClickListener{
            signUpUser()
        }
    }

    private fun signUpUser() {
        if(ip_username.text.toString().isEmpty()){
            ip_username.error = "Please enter email"
            ip_username.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(ip_username.text.toString()).matches()){
            ip_username.error = "Please enter a valid email"
            ip_username.requestFocus()
            return
        }
        if(ip_password.text.toString().isEmpty()){
            ip_password.error = "Please enter password"
            ip_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(ip_username.text.toString(), ip_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginScreen::class.java))
                                finish()
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Sign up failed, try again after some time",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }
}