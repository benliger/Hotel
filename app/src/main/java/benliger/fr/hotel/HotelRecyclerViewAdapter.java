package benliger.fr.hotel;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

import benliger.fr.hotel.listener.WeekendClick;
import benliger.fr.hotel.model.Hotel;
import benliger.fr.hotel.model.Weekend;
import benliger.fr.hotel.view.HotelViewHolder;
import benliger.fr.hotel.view.WeekendViewHolder;

public class HotelRecyclerViewAdapter
		extends ExpandableRecyclerAdapter<HotelViewHolder, WeekendViewHolder> {

	private WeekendClick mListener;

	public HotelRecyclerViewAdapter(@NonNull List<Hotel> hotelList, WeekendClick listener) {
		super(hotelList);
		mListener = listener;
	}

	@Override
	public HotelViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
		View viewHotel = LayoutInflater.from(parentViewGroup.getContext())
				.inflate(R.layout.hotel_list_content_parent, parentViewGroup, false);
		return new HotelViewHolder(viewHotel);
	}

	@Override
	public WeekendViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
		View viewWeekend = LayoutInflater.from(childViewGroup.getContext())
				.inflate(R.layout.hotel_list_content_child, childViewGroup, false);
		return new WeekendViewHolder(viewWeekend, mListener);
	}

	@Override
	public void onBindParentViewHolder(HotelViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
		Hotel hotel = (Hotel) parentListItem;
		parentViewHolder.bind(hotel);
	}

	@Override
	public void onBindChildViewHolder(WeekendViewHolder childViewHolder, int position, Object childListItem) {
		Weekend weekend = (Weekend) childListItem;
		childViewHolder.bind(weekend);
	}

}
