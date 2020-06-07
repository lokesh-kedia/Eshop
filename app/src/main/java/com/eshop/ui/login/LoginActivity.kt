package com.eshop.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eshop.MainActivity
import com.eshop.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_otp_field.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    var verificationId: String? = null
    var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        text_phone_number.requestFocus()

        text_phone_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                entered_number.text = p0.toString()
                btn_continue.isEnabled = p0?.length == 10
            }

        })
        first_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                second_number.requestFocus()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        second_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                third_number.requestFocus()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        third_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fourth_number.requestFocus()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        fourth_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fifth_number.requestFocus()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        fifth_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                sixth_number.requestFocus()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        sixth_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                decideLoginButtonState()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun decideLoginButtonState() {
        btn_login.isEnabled =
            !(first_number.text.isNullOrBlank() || second_number.text.isNullOrBlank() || third_number.text.isNullOrBlank() || fourth_number.text.isNullOrBlank() || fifth_number.text.isNullOrBlank() || sixth_number.text.isNullOrBlank())
    }

    fun onContinueClicked(view: View) {
        if (text_phone_number.text?.length == 10) {
            requestOtpFromFirebase()
            first_number.requestFocus()
            login_card.visibility = View.GONE
            otp_card.visibility = View.VISIBLE
        } else {
            text_phone_number.error = getString(R.string.invalid_number)
        }
    }


    private fun requestOtpFromFirebase() {
        val phoneNumber = "+91" + text_phone_number.text.toString()

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    Log.d("AuthTest", "invalid $e")
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.d("AuthTest", "Quota exceed $e")
                }

                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.verification_failed),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                this@LoginActivity.verificationId = verificationId
                this@LoginActivity.token = token.toString()
                Toast.makeText(this@LoginActivity, getString(R.string.otp_sent), Toast.LENGTH_LONG)
                    .show()
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks
        ) // OnVerificationStateChangedCallbacks
    }

    fun onChangeNumberClicked(view: View) {
        text_phone_number.requestFocus()
        login_card.visibility = View.VISIBLE
        otp_card.visibility = View.GONE
    }

    fun onLoginClicked(view: View) {

        val code =
            first_number.text.toString() + second_number.text + third_number.text + fourth_number.text + fifth_number.text + sixth_number.text
        verificationId?.let { verificationId ->
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.otp_verified),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.otp_failed),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
    }
}
