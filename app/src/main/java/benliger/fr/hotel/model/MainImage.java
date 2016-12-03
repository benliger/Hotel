package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainImage implements Parcelable {

	public static final Parcelable.Creator<MainImage> CREATOR = new Parcelable.Creator<MainImage>() {
		@Override
		public MainImage createFromParcel(Parcel source) {
			return new MainImage(source);
		}

		@Override
		public MainImage[] newArray(int size) {
			return new MainImage[size];
		}
	};

	private String url;

	public MainImage() {
	}

	protected MainImage(Parcel in) {
		this.url = in.readString();
	}

	public String getUrl() {
		return url;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.url);
	}

}
