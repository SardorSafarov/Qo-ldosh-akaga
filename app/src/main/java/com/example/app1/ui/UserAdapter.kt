package com.example.app1.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.R
import com.example.app1.databinding.ItemUserBinding
import com.example.app1.entity.User

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList = emptyList<User>()
    private var word=""

    fun setdata(user: List<User>,word:String) {
        this.word=word
        this.userList = user
        notifyDataSetChanged()
    }
       inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = ItemUserBinding.bind(itemView)
        fun bind(user: User) {
            val s="<b style='background-color:yellow;'>"+word+"</b>"
            val s1=user.ustun1.replace(word,s)
            val s2=user.ustun2.replace(word,s)
            val s3=user.ustun3.replace(word,s)
            val s4=user.ustun4.replace(word,s)
            binding.ustun1.text=Html.fromHtml(s1)
            binding.ustun2.text=Html.fromHtml(s2)
            binding.ustun3.text=Html.fromHtml(s3)
            binding.ustun4.text=Html.fromHtml(s4)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size


}