package com.example.vkappsynh.classes

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class VkUser(
    val id: Int = 0,
    val gender: Int = 0,
    val firstName: String? = "",
    val lastName: String? = "",
    val status: String? = "",
    val city: String? = "",
    val photo: String? = "",
    val deactivated: Boolean = false): Parcelable {

    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(status)
        dest.writeString(city)
        dest.writeString(photo)
        dest.writeByte(if (deactivated) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VkUser> {
        override fun createFromParcel(parcel: Parcel): VkUser {
            return VkUser(parcel)
        }

        override fun newArray(size: Int): Array<VkUser?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
                = VkUser(id = json.optInt("id", 0),
            gender = json.optInt("sex", 0),
            firstName = json.optString("first_name", ""),
            lastName = json.optString("last_name", ""),
            status = json.optString("status", ""),
            city = json.getJSONObject("city").optString("title", ""),
            photo = json.optString("photo_200", ""),
            deactivated = json.optBoolean("deactivated", false))

    }
}