package com.example.vkappsynh.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vkappsynh.R
import com.example.vkappsynh.fragments.DialogsFragment
import com.example.vkappsynh.fragments.FriendsFragment
import com.example.vkappsynh.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope

class MainActivity : AppCompatActivity() {

    var navigationView: BottomNavigationView? = null

    private var score = arrayListOf(
        VKScope.FRIENDS, VKScope.EMAIL, VKScope.STATUS, VKScope.PHOTOS
    )
    private var navigationlistner = object: BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.navigation_profile -> {
                    val profileFragment = ProfileFragment()
                    replaceFragment(profileFragment)
                    title = "Profile"
                    return true
                }
                R.id.navigation_friends -> {
                    val friendsFragment = FriendsFragment()
                    replaceFragment(friendsFragment)
                    title = "Friends"
                    return true
                }
                R.id.navigation_dialogs -> {
                    val dialogsFragment = DialogsFragment()
                    replaceFragment(dialogsFragment)
                    title = "Dialogs"
                    return true
                }
                R.id.navigation_logout ->{
                    VK.logout()
                    finish()
                    startActivity(Intent(applicationContext, AuthActivity::class.java))
                    return true
                }
            }
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView = findViewById(R.id.bottom_navigation)
        navigationView!!.setOnNavigationItemSelectedListener(navigationlistner)
        val profileFragment = ProfileFragment()
        replaceFragment(profileFragment)
        title = "Profile"
        /*if (!VK.isLoggedIn()){
            VK.login(this, score)
        } else{
            val profileFragment = ProfileFragment()
            replaceFragment(profileFragment)
            title = "Profile"
        }
        */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback{
            override fun onLogin(token: VKAccessToken) {
                Toast.makeText(applicationContext, "All right! It's working!\nWelcome to Synh's VK Application!", Toast.LENGTH_LONG).show()
                val profileFragment = ProfileFragment()
                replaceFragment(profileFragment)
                title = "Profile"
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(applicationContext, "I'm so sorry... It isn't working :c", Toast.LENGTH_LONG).show()
            }

        }
        if (data != null && VK.onActivityResult(requestCode, resultCode, data, callback)){
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()

    }
}
