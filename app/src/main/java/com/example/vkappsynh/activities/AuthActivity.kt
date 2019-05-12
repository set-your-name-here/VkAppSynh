package com.example.vkappsynh.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vkappsynh.R
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope

class AuthActivity : AppCompatActivity() {

    private var authButton: Button? = null

    private var score = arrayListOf(
        VKScope.FRIENDS, VKScope.EMAIL, VKScope.STATUS, VKScope.PHOTOS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authButton = findViewById(R.id.login_button)
        authButton!!.setOnClickListener {
            VK.login(this, score)
        }
        if (VK.isLoggedIn()){
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Toast.makeText(applicationContext, "All right! It's working!\nWelcome to Synh's VK Application!", Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(applicationContext, "I'm so sorry... It isn't working :c", Toast.LENGTH_LONG).show()
            }

        }
        if (data != null && VK.onActivityResult(requestCode, resultCode, data, callback)){
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
