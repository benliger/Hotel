package benliger.fr.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import benliger.fr.hotel.listener.WeekendClick;
import benliger.fr.hotel.model.Hotel;
import benliger.fr.hotel.model.HotelResponse;
import benliger.fr.hotel.model.Weekend;
import benliger.fr.hotel.service.HotelApi;
import benliger.fr.hotel.service.HotelRetrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomeActivity extends AppCompatActivity
		implements WeekendClick {

	private static final String TAG = "HomeActivity";
	private static final int NUMBER_HOTEL_TO_FETCH = 5;
	private static final int PAGE_SEARCH = 0;

	private CompositeSubscription mCompositeSubscription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_list);
		setupToolbar();

		mCompositeSubscription = new CompositeSubscription();
		fetchLastHotelsAsync();
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(getTitle());
	}

	@Override
	protected void onDestroy() {
		mCompositeSubscription.unsubscribe();
		super.onDestroy();
	}

	// =============================
	// PRIVATE METHODS
	// =============================

	private void setupRecyclerView(@NonNull RecyclerView recyclerView, @NonNull List<Hotel> hotelList) {
		HotelRecyclerViewAdapter adapter = new HotelRecyclerViewAdapter(hotelList, this);
		recyclerView.setAdapter(adapter);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
		recyclerView.addItemDecoration(dividerItemDecoration);

	}

	private void fetchLastHotelsAsync() {
		final HotelApi hotelApi = new HotelRetrofit().build();
		Observable<HotelResponse> lastHotelByPriceQuality = hotelApi.getLastHotelByPriceQuality(NUMBER_HOTEL_TO_FETCH, PAGE_SEARCH);

		mCompositeSubscription.add(
				lastHotelByPriceQuality.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(new Subscriber<HotelResponse>() {
							@Override
							public void onCompleted() {
								Log.d(TAG, "fetch last hotels completed");
							}

							@Override
							public void onError(Throwable e) {
								Log.e(TAG, "fetch last hotels on error: ", e);
							}

							@Override
							public void onNext(HotelResponse hotelResponse) {
								List<Hotel> hotelList = hotelResponse.getHotelList();
								Log.d(TAG, "fetch last hotels ok with " + hotelList.size() + " results");
								setupRecyclerView((RecyclerView) findViewById(R.id.hotel_list), hotelList);
							}
						}));
	}

	@Override
	public void onWeekendClicked(@Nullable Weekend weekend) {
		if (weekend != null) {
			Intent intent = new Intent(HomeActivity.this, WeekendDetailActivity.class);
			intent.putExtra(WeekendDetailActivity.ARG_ITEM_PARCEL, weekend);
			startActivity(intent);
		}
	}
}