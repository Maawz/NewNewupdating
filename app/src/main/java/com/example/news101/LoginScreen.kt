package com.example.news101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
//import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        auth = Firebase.auth

        btn_sign_up2.setOnClickListener{
            startActivity(Intent(this, SignUpScreen::class.java))
            finish()
        }

        button3.setOnClickListener{
            doLogin()
        }
    }

    private fun doLogin() {
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
        auth.signInWithEmailAndPassword(ip_username.text.toString(), ip_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    updateUI(null)
                    // ...
                }

                // ...
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseApp.initializeApp(this)
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

   private fun updateUI(currentUser: FirebaseUser?){

        if(currentUser != null){
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else{
                Toast.makeText(baseContext, "Please verify email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}