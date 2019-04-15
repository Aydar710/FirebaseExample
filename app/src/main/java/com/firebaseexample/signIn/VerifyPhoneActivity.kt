package com.firebaseexample.signIn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebaseexample.R
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerifyPhoneActivity : AppCompatActivity() {

    private var verificationId: String? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        auth = FirebaseAuth.getInstance()
        val phoneNumber = intent.getStringExtra(SignInActivity.PHONE_NUMBER)
        sendVerificationCode(phoneNumber)
    }

    private fun verifyCode(code: String) {
        val credential: PhoneAuthCredential? = verificationId?.let { PhoneAuthProvider.getCredential(it, code) }
    }

    private fun signinWithCredintial(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                if (it.isSuccessful)
                    Toast.makeText(this@VerifyPhoneActivity, "Succesfull", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this@VerifyPhoneActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun sendVerificationCode(number: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            verificationCallback
        )
    }

    private val verificationCallback =
        object :
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(s, p1)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential?) {
                val code: String = phoneAuthCredential?.smsCode.toString()
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                Toast.makeText(this@VerifyPhoneActivity, "Verification failed", Toast.LENGTH_SHORT).show()
            }
        }
}
