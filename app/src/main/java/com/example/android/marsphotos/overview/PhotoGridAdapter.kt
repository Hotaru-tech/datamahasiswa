/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.DetailUserActivity
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.Colors

class PhotoGridAdapter :
    ListAdapter<Colors, PhotoGridAdapter.ColorsViewHolder>(DiffCallback) {

    class ColorsViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(colors: Colors) {
            binding.photo = colors
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Colors>() {
        override fun areItemsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Colors, newItem: Colors): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorsViewHolder {
        return ColorsViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val colors = getItem(position)
        holder.bind(colors)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val gambar: String = colors.imgSrcUrl
            val nama : String = colors.nama
            val nim: String = colors.nim
            val alamat = colors.alamat
            val deskripsi = colors.deskripsi

            //Membuat Intent

            val intent = Intent(context, DetailUserActivity::class.java)
            //Putextra
            intent.putExtra("Gambar", gambar)
            intent.putExtra("Nama", nama)
            intent.putExtra("Nim" , nim)
            intent.putExtra("Alamat", alamat)
            intent.putExtra("Deskripsi", deskripsi)
            context.startActivity(intent)
        }

    }
}
