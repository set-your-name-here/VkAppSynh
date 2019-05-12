package com.example.vkappsynh.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.vkappsynh.R
import com.example.vkappsynh.classes.VkFriend
import com.squareup.picasso.Picasso

class FriendListAdapter(
    private var context: Context,
    private var dataSource: List<VkFriend>) : BaseAdapter(){

    private var inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.friends_list_layout, parent, false)
        val listImage = view.findViewById(R.id.list_image) as ImageView
        val listName = view.findViewById(R.id.list_friend_name) as TextView
        val listOnline = view.findViewById(R.id.list_online) as TextView

        val item = getItem(position) as VkFriend
        val strBuilder = StringBuilder()
        strBuilder
            .append(item.firstName)
            .append(" ")
            .append(item.lastName)
        listName.text = strBuilder.toString()

        when (item.online){
            0 -> listOnline.text = "offline"
            1 ->{
                listOnline.text = "online"
                listOnline.setTextColor(context.getColor(R.color.colorOnline))
            }
        }

        Picasso.get().load(item.photo).into(listImage)
        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}