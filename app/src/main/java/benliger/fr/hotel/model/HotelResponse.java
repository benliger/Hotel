package benliger.fr.hotel.model;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class HotelResponse {

	private SearchDetails searchDetails;
	@Json(name = "exactMatch")
	private List<Hotel> hotelList = new ArrayList<>();

	public List<Hotel> getHotelList() {
		return hotelList;
	}
}
