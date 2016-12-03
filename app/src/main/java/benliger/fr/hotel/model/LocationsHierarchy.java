package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationsHierarchy implements Parcelable {

	public static final Parcelable.Creator<LocationsHierarchy> CREATOR = new Parcelable.Creator<LocationsHierarchy>() {
		@Override
		public LocationsHierarchy createFromParcel(Parcel source) {
			return new LocationsHierarchy(source);
		}

		@Override
		public LocationsHierarchy[] newArray(int size) {
			return new LocationsHierarchy[size];
		}
	};

	private String type;
	private String label;
	private int id;

	public LocationsHierarchy() {
	}

	protected LocationsHierarchy(Parcel in) {
		this.type = in.readString();
		this.label = in.readString();
		this.id = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.type);
		dest.writeString(this.label);
		dest.writeInt(this.id);
	}
}
