package com.mywallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mywallet.databinding.ItemRecyclerBinding

class AdapterRecView(private val listKategori: ArrayList<DcKategori>) :
    RecyclerView.Adapter<AdapterRecView.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun ubahData(pos: Int)
        fun delData(pos: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {

        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listKategori.size
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val kategori = listKategori[position]

        holder.binding.tvData.text =
            "${kategori.nama}\n${kategori.jenis}"

        holder.binding.btnUbah.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition

            if (currentPosition != RecyclerView.NO_POSITION &&
                ::onItemClickCallback.isInitialized) {
                onItemClickCallback.ubahData(currentPosition)
            }
        }

        holder.binding.btnHapus.setOnClickListener {
            val currentPosition = holder.bindingAdapterPosition

            if (currentPosition != RecyclerView.NO_POSITION &&
                ::onItemClickCallback.isInitialized) {
                onItemClickCallback.delData(currentPosition)
            }
        }
    }
}