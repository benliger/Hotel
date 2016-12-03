package benliger.fr.hotel.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.squareup.picasso.Picasso;

import benliger.fr.hotel.R;
import benliger.fr.hotel.listener.WeekendClick;
import benliger.fr.hotel.model.Weekend;

public class WeekendViewHolder extends ChildViewHolder {

	private static final String DELIMITER_THEME = ", ";

	private Context mContext;
	private TextView mLabelTextView;
	private ImageView mImageView;
	private TextView mThemeTextView;

	private Weekend mWeekend;

	public WeekendViewHolder(@NonNull View itemView, @Nullable final WeekendClick listener) {
		super(itemView);
		mContext = itemView.getContext();
		mLabelTextView = (TextView) itemView.findViewById(R.id.weekend_label);
		mThemeTextView = (TextView) itemView.findViewById(R.id.weekend_theme);
		mImageView = (ImageView) itemView.findViewById(R.id.weekend_image);

		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (listener != null) {
					listener.onWeekendClicked(mWeekend);
				}
			}
		});
	}

	public void bind(@NonNull Weekend weekend) {
		mWeekend = weekend;

		mLabelTextView.setText(mWeekend.getLabel());
		mThemeTextView.setText(TextUtils.join(DELIMITER_THEME, mWeekend.getTopTheme()));

		Picasso.with(mContext)
				.load(mWeekend.getImageUrl())
				.fit()
				.into(mImageView);
	}

}