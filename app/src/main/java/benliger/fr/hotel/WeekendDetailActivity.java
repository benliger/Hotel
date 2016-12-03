package benliger.fr.hotel;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import benliger.fr.hotel.model.Weekend;

public class WeekendDetailActivity extends AppCompatActivity {

	public static final String ARG_ITEM_PARCEL = "benliger.fr.hotel.WeekendDetailActivity.item";
	private static final String DELIMITER_THEME = ", ";
	private static final String DELIMITER_PROGRAM = "<br />";

	private Weekend mWeekend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekend_detail);

		Bundle bundleData = savedInstanceState == null ? getIntent().getExtras() : savedInstanceState;
		mWeekend = bundleData.getParcelable(ARG_ITEM_PARCEL);

		setupToolbar();
		setupLayoutInfo();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle saveInstanceState) {
		super.onSaveInstanceState(saveInstanceState);
		saveInstanceState.putParcelable(ARG_ITEM_PARCEL, mWeekend);
	}

	// =============================
	// PRIVATE METHODS
	// =============================

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
		setSupportActionBar(toolbar);

		// Show the Up button in the action bar.
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
		appBarLayout.setTitle(mWeekend.getLabel());

		ImageView toolbarImage = (ImageView) appBarLayout.findViewById(R.id.toolbar_image);
		Picasso.with(this)
				.load(mWeekend.getImageUrl())
				.fit()
				.into(toolbarImage);
	}

	private void setupLayoutInfo() {
		TextView labelText = (TextView) findViewById(R.id.weekend_detail_label);
		labelText.setText(mWeekend.getLabel());

		TextView detailText = (TextView) findViewById(R.id.weekend_detail_theme);
		detailText.setText(TextUtils.join(DELIMITER_THEME, mWeekend.getTopTheme()));

		TextView priceText = (TextView) findViewById(R.id.weekend_detail_price);
		priceText.setText(getString(R.string.weekend_detail_price_formatted_sell_price, mWeekend.getPrice().getSellPrice()));

		TextView programIntro = (TextView) findViewById(R.id.weekend_detail_program_intro);
		programIntro.setText(Html.fromHtml(TextUtils.join(DELIMITER_PROGRAM, mWeekend.getProgramIntro())));

		// ...
	}

}
