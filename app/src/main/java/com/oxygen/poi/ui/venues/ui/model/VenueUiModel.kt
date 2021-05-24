package com.oxygen.poi.ui.venues.ui.model

import com.oxygen.domain.venues.model.VenueModel

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
data class VenueUiModel(
  val id: String,
  val name: String,
  val address: String?
) {

  companion object {
    fun from(venueModel: VenueModel) =
      VenueUiModel(
        venueModel.id,
        venueModel.name,
        venueModel.location.address
      )
  }

}
