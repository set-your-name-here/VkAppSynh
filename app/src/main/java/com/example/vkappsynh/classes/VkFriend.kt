package com.example.vkappsynh.classes

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class VkFriend(
    val id: Int = 0,
    val online: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    val photo: String? = null): Parcelable{

    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeInt(online)
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VkFriend> {
        override fun createFromParcel(parcel: Parcel): VkFriend {
            return VkFriend(parcel)
        }

        override fun newArray(size: Int): Array<VkFriend?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
            = VkFriend(
                id = json.optInt("id",0),
                online = json.optInt("online", 0),
                firstName = json.optString("first_name",""),
                lastName = json.optString("last_name", ""),
                photo = json.optString("photo_100", "")
        )
    }
}