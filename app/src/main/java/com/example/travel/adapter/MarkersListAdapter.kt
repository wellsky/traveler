package com.example.travel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travel.databinding.MarkersListItemBinding
import com.example.travel.dto.MapMarker


interface OnInteractionListener {
    fun onClick(marker: MapMarker) {}
}

class MarkersListAdapter (
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<MapMarker, MarkerItemViewHolder>(MarkerItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerItemViewHolder {
        val binding = MarkersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarkerItemViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MarkerItemViewHolder, position: Int) {
        val area = getItem(position)
        holder.bind(area)
    }
}

class MarkerItemViewHolder(
    private val binding: MarkersListItemBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(marker: MapMarker) {
        binding.apply {
            markerName.text = marker.name
            markerText.text = marker.text


            root.setOnClickListener {
                onInteractionListener.onClick(marker)
            }
        }
    }
}

class MarkerItemDiffCallback : DiffUtil.ItemCallback<MapMarker>() {
    override fun areItemsTheSame(oldItem: MapMarker, newItem: MapMarker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MapMarker, newItem: MapMarker): Boolean {
        return oldItem == newItem
    }
}