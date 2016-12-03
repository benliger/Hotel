package benliger.fr.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomeActivity extends AppCompatActivity
        implements WeekendClick {

    private static final String TAG = "HomeActivity";
    private static final int NUMBER_HOTEL_TO_FETCH = 5;
    private static final int PAGE_SEARCH = 0;

    private CompositeSubscription mCompositeSubscription;
    private ProgressBar mProgressView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        setupToolbar();
        mProgressView = (ProgressBar) findViewById(android.R.id.progress);

        mCompositeSubscription = new CompositeSubscription();
        fetchLastHotelsAsync();
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());
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
                        .map(new Func1<HotelResponse, List<Hotel>>() {

                            @Override
                            public List<Hotel> call(HotelResponse hotelResponse) {
                                return hotelResponse.getHotelList();
                            }
                        })
                        .subscribe(new Subscriber<List<Hotel>>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "fetch last hotels completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "fetch last hotels on error: ", e);
                                mProgressView.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(mToolbar, R.string.fetch_last_hotels_failed, Snackbar.LENGTH_LONG);
                                snackbar.setAction(R.string.action_retry, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        fetchLastHotelsAsync();
                                    }
                                });
                                snackbar.show();
                            }

                            @Override
                            public void onNext(List<Hotel> hotelList) {
                                Log.d(TAG, "fetch last hotels ok with " + hotelList.size() + " results");
                                mProgressView.setVisibility(View.GONE);
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