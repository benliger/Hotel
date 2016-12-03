package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchDetails implements Parcelable {

	public static final Parcelable.Creator<SearchDetails> CREATOR = new Parcelable.Creator<SearchDetails>() {
		@Override
		public SearchDetails createFromParcel(Parcel source) {
			return new SearchDetails(source);
		}

		@Override
		public SearchDetails[] newArray(int size) {
			return new SearchDetails[size];
		}
	};

	private String searchKey;
	private int totalCount;
	private int adult;
	private int child;
	private int baby;
	private boolean singleRoom;
	private String uri;

	public SearchDetails() {
	}

	protected SearchDetails(Parcel in) {
		this.searchKey = in.readString();
		this.totalCount = in.readInt();
		this.adult = in.readInt();
		this.child = in.readInt();
		this.baby = in.readInt();
		this.singleRoom = in.readByte() != 0;
		this.uri = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.searchKey);
		dest.writeInt(this.totalCount);
		dest.writeInt(this.adult);
		dest.writeInt(this.child);
		dest.writeInt(this.baby);
		dest.writeByte(this.singleRoom ? (byte) 1 : (byte) 0);
		dest.writeString(this.uri);
	}

}
