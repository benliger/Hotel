package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

public class Hotel implements Parcelable, ParentListItem {

	public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
		@Override
		public Hotel createFromParcel(Parcel source) {
			return new Hotel(source);
		}

		@Override
		public Hotel[] newArray(int size) {
			return new Hotel[size];
		}
	};

	private int id;
	private String label;
	private Location location;
	private Review review;
	private List<Weekend> weekend = new ArrayList<>();
	private List<String> facility = new ArrayList<>();
	private int star;
	private boolean connectivity;
	private MainImage mainImage;

	public Hotel() {
	}

	protected Hotel(Parcel in) {
		this.id = in.readInt();
		this.label = in.readString();
		this.location = in.readParcelable(Location.class.getClassLoader());
		this.review = in.readParcelable(Review.class.getClassLoader());
		this.weekend = new ArrayList<>();
		in.readList(this.weekend, Weekend.class.getClassLoader());
		this.facility = in.createStringArrayList();
		this.star = in.readInt();
		this.connectivity = in.readByte() != 0;
		this.mainImage = in.readParcelable(MainImage.class.getClassLoader());
	}

	public String getLabel() {
		return label;
	}

	public Location getLocation() {
		return location;
	}

	public Review getReview() {
		return review;
	}

	public MainImage getMainImage() {
		return mainImage;
	}

	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.label);
		dest.writeParcelable(this.location, flags);
		dest.writeParcelable(this.review, flags);
		dest.writeList(this.weekend);
		dest.writeStringList(this.facility);
		dest.writeInt(this.star);
		dest.writeByte(this.connectivity ? (byte) 1 : (byte) 0);
		dest.writeParcelable(this.mainImage, flags);
	}

	@Override
	public List<Weekend> getChildItemList() {
		return weekend;
	}

	@Override
	public boolean isInitiallyExpanded() {
		return !weekend.isEmpty();
	}
}
