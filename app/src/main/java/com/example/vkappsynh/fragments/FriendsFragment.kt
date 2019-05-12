package com.example.vkappsynh.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast

import com.example.vkappsynh.R
import com.example.vkappsynh.adapters.FriendListAdapter
import com.example.vkappsynh.classes.VkFriend
import com.example.vkappsynh.classes.VkFriendRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

class FriendsFragment : Fragment() {

    private var listView: ListView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        listView = view.findViewById(R.id.listFriend)
        loadFriendList()
        return view
    }

    private fun loadFriendList(){
        VK.execute(VkFriendRequest(), object: VKApiCallback<List<VkFriend>>{
            override fun fail(error: VKApiExecutionException) {
                Toast.makeText(view!!.context, "I'm so sorry... It isn't working :c", Toast.LENGTH_LONG).show()
            }

            override fun success(result: List<VkFriend>) {
                val adapter = FriendListAdapter(view!!.context, result)
                listView!!.adapter = adapter
            }

        })
    }


}
