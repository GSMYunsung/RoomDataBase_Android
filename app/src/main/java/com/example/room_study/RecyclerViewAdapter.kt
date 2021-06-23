package com.example.room_study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room_study.DB.UserEntity
import com.example.room_study.databinding.ActivityMainBinding
import com.example.room_study.databinding.RecyclearviewRowBinding

class RecyclerViewAdapter(val listener : RowClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()
    fun setlistData(data: ArrayList<UserEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        var inflate = LayoutInflater.from(parent.context).inflate(R.layout.recyclearview_row, parent,false)
        return MyViewHolder(inflate,listener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            //값 넣어주는부분이라네요~
            listener.onItemClikListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view : View, val listener: RowClickListener) : RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val deleteUserId = view.findViewById<View>(R.id.deleteUserID)

        fun bind(data : UserEntity){
            tvName.text = data.name
            tvEmail.text = data.email

            deleteUserId.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    //재활용 클릭처리
    interface RowClickListener{
        fun onDeleteUserClickListener(user : UserEntity)
        fun onItemClikListener(user: UserEntity)
    }
}