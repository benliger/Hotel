package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

	public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
		@Override
		public Review createFromParcel(Parcel source) {
			return new Review(source);
		}

		@Override
		public Review[] newArray(int size) {
			return new Review[size];
		}
	};

	private float average;
	private int count;

	public Review() {
	}

	protected Review(Parcel in) {
		this.average = in.readFloat();
		this.count = in.readInt();
	}

	public float getAverage() {
		return average;
	}

	public int getCount() {
		return count;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(this.average);
		dest.writeInt(this.count);
	}


}
