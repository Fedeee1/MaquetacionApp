package com.example.maquetacionapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.maquetacionapp.R
import com.example.maquetacionapp.data.User
import com.squareup.picasso.Picasso

class ViewPagerAdapter(viewPagerList: List<User>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var viewPagerList = viewPagerList

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imgUser: ImageView
        var txtDescriptionUser: TextView
        var txtNameUser: TextView
        var txtAgeUser: TextView
        var txtSexUser: TextView

        init {
            imgUser = itemView.findViewById(R.id.imgUser)
            txtDescriptionUser = itemView.findViewById(R.id.txtDescriptionUser)
            txtNameUser = itemView.findViewById(R.id.txtNameUser)
            txtAgeUser = itemView.findViewById(R.id.txtAgeUser)
            txtSexUser = itemView.findViewById(R.id.txtSexUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewHolder, position: Int) {
        var image = Uri.parse(viewPagerList[position].image)
        holder.txtNameUser.text = viewPagerList[position].name
        holder.txtAgeUser.text = viewPagerList[position].age.toString()
        holder.txtSexUser.text = viewPagerList[position].sex
        holder.txtDescriptionUser.text = viewPagerList[position].description
        Picasso.get()
             .load(image)
             .error(R.drawable.icoon_feed)
             .into(holder.imgUser)
    }

    override fun getItemCount(): Int {
        return viewPagerList.size
    }

}

