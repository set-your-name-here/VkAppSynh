package com.example.vkappsynh.fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.vkappsynh.R
import com.example.vkappsynh.classes.VkUser
import com.example.vkappsynh.classes.VkUsersRequest
import com.squareup.picasso.Picasso
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

class ProfileFragment : Fragment() {

    var txtUsername: TextView? = null
    var txtStatus: TextView? = null
    var txtBDate: TextView? = null
    var txtGender: TextView? = null
    var txtCity: TextView? = null

    var imgPhoto: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        txtUsername = view.findViewById(R.id.txtUsername)
        txtStatus = view.findViewById(R.id.txtStatus)
        txtBDate = view.findViewById(R.id.txtAgeValue)
        txtGender = view.findViewById(R.id.txtGenderValue)
        txtCity = view.findViewById(R.id.txtCityValue)
        imgPhoto = view.findViewById(R.id.imgProfile)
        loadUserData()
        return view
    }

    private fun loadUserData(){
        VK.execute(VkUsersRequest(), object: VKApiCallback<List<VkUser>>{
            @SuppressLint("SetTextI18n")
            override fun success(result: List<VkUser>) {
                val user = result[0]
                val builder = StringBuilder()
                builder.append(user.firstName).append(" ").append(user.lastName)
                txtUsername!!.text = builder.toString()
                txtStatus!!.text = user.status
                txtCity!!.text = user.city
                txtBDate!!.text = user.bdate
                when (user.gender){
                    2 -> txtGender!!.text = "Male"
                    1 -> txtGender!!.text = "Female"
                    0 -> txtGender!!.text = "Undefined"
                }
                Picasso.get().load(user.photo).into(imgPhoto)
                //val imageUri = Uri.parse(user.photo)
                //imgPhoto!!.setImageURI(imageUri)
            }

            override fun fail(error: VKApiExecutionException) {
                Toast.makeText(view!!.context, "I'm so sorry... It isn't working :c", Toast.LENGTH_LONG).show()
            }


        })
    }

}
