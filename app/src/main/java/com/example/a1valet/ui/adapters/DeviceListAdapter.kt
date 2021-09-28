package com.example.a1valet.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1valet.data.models.Device

class DeviceListAdapter(private val propertyClick: (String?) -> Unit) :
    RecyclerView.Adapter<DeviceListViewHolder>() {
    private var deviceData: List<Device>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceListViewHolder {
        return DeviceListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        deviceData?.get(position)?.let { data ->
            holder.bind(data)
            holder.itemView.setOnClickListener {
                propertyClick.invoke(data.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return deviceData?.size ?: 0
    }

    fun submitList(deviceData: List<Device>) {
        this.deviceData = deviceData
        notifyDataSetChanged()
    }
}
