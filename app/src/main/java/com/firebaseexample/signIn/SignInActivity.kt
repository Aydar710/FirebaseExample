package com.firebaseexample.signIn

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebaseexample.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        btn_authenticate.setOnClickListener {
            val number : String = et_phone_number.text.toString()

            val intent = Intent(this, VerifyPhoneActivity::class.java)
            intent.putExtra(PHONE_NUMBER, number)
            startActivity(intent)
        }
    }

    companion object {
        const val PHONE_NUMBER = "phone_number"
    }
}
