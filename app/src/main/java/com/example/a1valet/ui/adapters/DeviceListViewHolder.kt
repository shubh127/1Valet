package com.example.a1valet.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valet.data.models.Device
import com.example.a1valet.databinding.DeviceItemViewBinding

class DeviceListViewHolder(private val binding: DeviceItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Device) {
        binding.device = data
    }

    companion object {
        fun create(parent: ViewGroup): DeviceListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                DeviceItemViewBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            return DeviceListViewHolder(binding)
        }
    }
}