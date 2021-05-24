package com.oxygen.poi.ui.venues.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel

class VenuesDiffCallback(
  private val oldItems: List<VenueUiModel>,
  private val newItems: List<VenueUiModel>
) : DiffUtil.Callback() {

  override fun getOldListSize(): Int = oldItems.size

  override fun getNewListSize(): Int = newItems.size

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldItems[oldItemPosition].id == newItems[newItemPosition].id

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
    oldItems[oldItemPosition] == newItems[newItemPosition]

}