package benliger.fr.hotel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import benliger.fr.hotel.R;
import benliger.fr.hotel.model.Hotel;

public class HotelViewHolder extends ParentViewHolder {

	private Context mContext;
	private TextView mLabelTextView;
	private TextView mPositionTextView;
	private TextView mReviewTextView;

	public HotelViewHolder(@NonNull View itemView) {
		super(itemView);
		mContext = itemView.getContext();
		mLabelTextView = (TextView) itemView.findViewById(R.id.hotel_label);
		mPositionTextView = (TextView) itemView.findViewById(R.id.hotel_position);
		mReviewTextView = (TextView) itemView.findViewById(R.id.hotel_review);
	}

	public void bind(@NonNull Hotel hotel) {
		mLabelTextView.setText(hotel.getLabel());
		mPositionTextView.setText(hotel.getLocation().getAddress());
		mReviewTextView.setText(mContext.getString(R.string.hotel_list_review_formatted_avg_count, hotel.getReview().getAverage(), hotel.getReview().getCount()));
	}

	@Override
	public boolean shouldItemViewClickToggleExpansion() {
		return false;
	}
}