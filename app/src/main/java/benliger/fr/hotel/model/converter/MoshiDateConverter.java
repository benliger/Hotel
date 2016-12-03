package benliger.fr.hotel.model.converter;

import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Adapter json pour les java.util.Date et le format string de type "2016-12-31"
 */
public final class MoshiDateConverter {
	private static final String TAG = "MoshiDateConverter";

	@ToJson
	public String toJson(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		return format.format(date);
	}

	@FromJson
	public Date fromJson(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
			return format.parse(date);

		} catch (ParseException e) {
			Log.e(TAG, "Impossible de parser la date " + date + " on met la Date(0) par défaut", e);
			return new Date(0);
		}

	}
}
