package com.keuapps.baseapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.keuapps.baseapp.data.remote.model.Kisiler
import com.keuapps.baseapp.databinding.ContactRowBinding

class ContactRecyclerAdapter : RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>() {

    class ContactViewHolder(val binding: ContactRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Kisiler>() {
        override fun areItemsTheSame(oldItem: Kisiler, newItem: Kisiler): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Kisiler, newItem: Kisiler): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var contacts : List<Kisiler>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size

    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.rowEtId.text = contacts.get(position).kisi_id
        holder.binding.rowEtName.text = contacts.get(position).kisi_ad
        holder.binding.rowEtPhone.text = contacts.get(position).kisi_tel
        //Picasso.get().load(postList[position].downloadUrl).into(holder.binding.recyclerImageView)
    }


}