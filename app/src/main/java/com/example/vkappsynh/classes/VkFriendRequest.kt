package com.example.vkappsynh.classes

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VkFriendRequest : VKRequest<List<VkFriend>>("friends.get"){
    init {
        addParam("order", "hints")
        addParam("fields", "name,photo_100")
        addParam("offset", 0)
    }

    override fun parse(r: JSONObject): List<VkFriend> {
        val friends = r.getJSONObject("response").getJSONArray("items")
        val count = r.getJSONObject("response").optInt("count")
        val result = ArrayList<VkFriend>()
        for (i in 0 until count){
            result.add(VkFriend.parse(friends.getJSONObject(i)))
        }
        return result
    }
}