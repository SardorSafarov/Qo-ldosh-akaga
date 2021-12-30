package com.example.app1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.R
import com.example.app1.databinding.ItemUserBinding
import com.example.app1.entity.User

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList = emptyList<User>()

    fun setdata(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
       inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = ItemUserBinding.bind(itemView)
        fun bind(user: User) {
            binding.ustun1.text=user.ustun1
            binding.ustun2.text=user.ustun2
            binding.ustun3.text=user.ustun3
            binding.ustun4.text=user.ustun4

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