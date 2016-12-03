package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {

	public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
		@Override
		public Price createFromParcel(Parcel source) {
			return new Price(source);
		}

		@Override
		public Price[] newArray(int size) {
			return new Price[size];
		}
	};

	private int sellPrice;
	private int refPrice;
	private String target;
	private int nights;
	private int nbRooms;

	public Price() {
	}

	protected Price(Parcel in) {
		this.sellPrice = in.readInt();
		this.refPrice = in.readInt();
		this.target = in.readString();
		this.nights = in.readInt();
		this.nbRooms = in.readInt();
	}

	public int getSellPrice() {
		return sellPrice;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.sellPrice);
		dest.writeInt(this.refPrice);
		dest.writeString(this.target);
		dest.writeInt(this.nights);
		dest.writeInt(this.nbRooms);
	}

}
