package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Data
import com.example.notesapp.databinding.RowTodosBinding

class Adapter(
    private val listener: OnItemClickListener,
): ListAdapter<Data, Adapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(private val binding: RowTodosBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val myData = getItem(position)
                        if (myData != null){
                            listener.onItemClickListener(myData,position)
                        }
                    }
                }


            }
        }

        fun bind(myData: Data){
            binding.apply {
                tvTitle.text = myData.title
                tvDesc.text = myData.desc

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener{
        fun onItemClickListener(myData: Data,position: Int)
    }
}