package org.brightmindenrichment.street_care.ui.home.what_to_give

import org.brightmindenrichment.street_care.R
import org.brightmindenrichment.street_care.ui.home.data.ItemsToGive

class DataSource {
    fun loadWhatToGiveItems(): List<ItemsToGive> {
        return listOf(
            ItemsToGive(R.drawable.icon_snacks, R.string.healthy_snacks),
            ItemsToGive(R.drawable.icon_water, R.string.water),
            ItemsToGive(R.drawable.icon_bandages, R.string.first_aid),
            ItemsToGive(R.drawable.icon_soap, R.string.personal_hygiene),
            ItemsToGive(R.drawable.icon_clothes, R.string.sock_clothing),
            ItemsToGive(R.drawable.icon_feminine, R.string.feminine_hygiene)

        )
    }
}