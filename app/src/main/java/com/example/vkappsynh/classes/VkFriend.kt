package com.example.vkappsynh.classes

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class VkFriend(
    val id: Int = 0,
    val name: String? = null): Parcelable{

    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
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
                id = json.optInt("",0),
                name = json.optString("","")
        )
    }
}