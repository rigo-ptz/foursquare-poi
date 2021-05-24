package com.oxygen.poi.ui.venues.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oxygen.poi.R
import com.oxygen.poi.databinding.ItemVenueBinding
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel

class VenuesAdapter : RecyclerView.Adapter<VenueViewHolder>() {

  private val items = mutableListOf<VenueUiModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
    val binding = DataBindingUtil.inflate<ItemVenueBinding>(
      LayoutInflater.from(parent.context),
      R.layout.item_venue,
      parent,
      false
    )
    return VenueViewHolder(binding)
  }

  override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun onBindViewHolder(
    holder: VenueViewHolder,
    position: Int,
    payloads: MutableList<Any>
  ) {
    if (payloads.isEmpty()) {
      onBindViewHolder(holder, position)
    }
  }

  override fun getItemCount(): Int = items.size

  override fun getItemId(position: Int): Long = items[position].id.hashCode().toLong()

  fun setData(
    newItems: List<VenueUiModel>,
    recycler: RecyclerView
  ) {
    val savedRecyclerState = recycler.layoutManager?.onSaveInstanceState()
    val diffResult = DiffUtil.calculateDiff(VenuesDiffCallback(items, newItems))
    items.clear()
    items.addAll(newItems)
    diffResult.dispatchUpdatesTo(this)
    recycler.layoutManager?.onRestoreInstanceState(savedRecyclerState)
  }

}

class VenueViewHolder(
  private val binding: ItemVenueBinding,
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(model: VenueUiModel) {
    binding.item = model
  }

}