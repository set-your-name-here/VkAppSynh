package com.example.vkappsynh

import android.app.Application
import android.content.Intent
import com.example.vkappsynh.activities.MainActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class MainApplication: Application(){

    private var tokenTracker = object: VKTokenExpiredHandler {
        override fun onTokenExpired() {
            val intent = Intent(this@MainApplication, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    override fun onCreate() {
        super.onCreate()
        VK.initialize(this)
        VK.addTokenExpiredHandler(tokenTracker)
    }

}