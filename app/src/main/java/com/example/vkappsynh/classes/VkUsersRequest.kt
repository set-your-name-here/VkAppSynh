package com.example.vkappsynh.classes

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VkUsersRequest(uids: IntArray = intArrayOf()) : VKRequest<List<VkUser>>("users.get") {
    init {
        if (uids.isNotEmpty()){
            addParam("user_ids", uids.joinToString(","))
        }
        addParam("fields", "photo_200")
    }

    override fun parse(r: JSONObject): List<VkUser> {
        val users = r.getJSONArray("response")
        val result = ArrayList<VkUser>()
        for (i in 0 until users.length()){
            result.add(VkUser.parse(users.getJSONObject(i)))
        }
        return result
    }
}