package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Location implements Parcelable {

	public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
		@Override
		public Location createFromParcel(Parcel source) {
			return new Location(source);
		}

		@Override
		public Location[] newArray(int size) {
			return new Location[size];
		}
	};

	private float longitude;
	private float latitude;
	private String label;
	private CompleteAddress completeAddress;
	private List<LocationsHierarchy> locationsHierarchy = new ArrayList<>();
	private float lat;
	private float lng;
	private String address;

	public Location() {
	}

	protected Location(Parcel in) {
		this.longitude = in.readFloat();
		this.latitude = in.readFloat();
		this.label = in.readString();
		this.completeAddress = in.readParcelable(CompleteAddress.class.getClassLoader());
		this.locationsHierarchy = new ArrayList<>();
		in.readList(this.locationsHierarchy, LocationsHierarchy.class.getClassLoader());
		this.lat = in.readFloat();
		this.lng = in.readFloat();
		this.address = in.readString();
	}

	public String getAddress() {
		return address;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(this.longitude);
		dest.writeFloat(this.latitude);
		dest.writeString(this.label);
		dest.writeParcelable(this.completeAddress, flags);
		dest.writeList(this.locationsHierarchy);
		dest.writeFloat(this.lat);
		dest.writeFloat(this.lng);
		dest.writeString(this.address);
	}

}
