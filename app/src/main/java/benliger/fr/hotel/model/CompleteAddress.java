package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CompleteAddress implements Parcelable {

	public static final Parcelable.Creator<CompleteAddress> CREATOR = new Parcelable.Creator<CompleteAddress>() {
		@Override
		public CompleteAddress createFromParcel(Parcel source) {
			return new CompleteAddress(source);
		}

		@Override
		public CompleteAddress[] newArray(int size) {
			return new CompleteAddress[size];
		}
	};

	private String number;
	private String street;
	private String zipCode;
	private String cityName;

	public CompleteAddress() {
	}

	protected CompleteAddress(Parcel in) {
		this.number = in.readString();
		this.street = in.readString();
		this.zipCode = in.readString();
		this.cityName = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.number);
		dest.writeString(this.street);
		dest.writeString(this.zipCode);
		dest.writeString(this.cityName);
	}
}
