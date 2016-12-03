package benliger.fr.hotel.service;


import benliger.fr.hotel.model.HotelResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * All APIs should be listed here
 */
public interface HotelApi {

	/**
	 *limit={count}&page={page}
	 * @param count number of Hotel wanted
	 * @param page page to search
	 * @return the last hotel order by price quality (in french)
	 */
	@GET("weekends.json?orderBy=priceQuality&locale=fr_FR")
	Observable<HotelResponse> getLastHotelByPriceQuality(
			@Query("limit") int count,
			@Query("page") int page
	);
}
