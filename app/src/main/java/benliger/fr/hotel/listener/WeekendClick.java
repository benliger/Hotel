package benliger.fr.hotel.listener;

import benliger.fr.hotel.HotelRecyclerViewAdapter;
import benliger.fr.hotel.model.Weekend;

/**
 * Interface pour gérer les clics sur les {@link Weekend} dans la liste gérer par {@link HotelRecyclerViewAdapter}
 */
public interface WeekendClick {
	void onWeekendClicked(Weekend weekend);
}
