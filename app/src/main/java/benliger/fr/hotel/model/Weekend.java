package benliger.fr.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Weekend implements Parcelable {

	public static final Parcelable.Creator<Weekend> CREATOR = new Parcelable.Creator<Weekend>() {
		@Override
		public Weekend createFromParcel(Parcel source) {
			return new Weekend(source);
		}

		@Override
		public Weekend[] newArray(int size) {
			return new Weekend[size];
		}
	};

	private int id;
	private String label;
	private Price price;
	private String imageUrl;
	private List<String> topTheme = new ArrayList<>();
	private List<String> programIntro = new ArrayList<>();
	private String uri;
	private List<Integer> tags = new ArrayList<>();
	private Date expiracy;
	private boolean flashDeal;
	private boolean lastMinute;

	public Weekend() {
	}

	protected Weekend(Parcel in) {
		this.id = in.readInt();
		this.label = in.readString();
		this.price = in.readParcelable(Price.class.getClassLoader());
		this.imageUrl = in.readString();
		this.topTheme = in.createStringArrayList();
		this.programIntro = in.createStringArrayList();
		this.uri = in.readString();
		this.tags = new ArrayList<>();
		in.readList(this.tags, Integer.class.getClassLoader());
		long tmpExpiracy = in.readLong();
		this.expiracy = tmpExpiracy == -1 ? null : new Date(tmpExpiracy);
		this.flashDeal = in.readByte() != 0;
		this.lastMinute = in.readByte() != 0;
	}

	public String getLabel() {
		return label;
	}

	public List<String> getTopTheme() {
		return topTheme;
	}

	public List<String> getProgramIntro() {
		return programIntro;
	}

	public Price getPrice() {
		return price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.label);
		dest.writeParcelable(this.price, flags);
		dest.writeString(this.imageUrl);
		dest.writeStringList(this.topTheme);
		dest.writeStringList(this.programIntro);
		dest.writeString(this.uri);
		dest.writeList(this.tags);
		dest.writeLong(this.expiracy != null ? this.expiracy.getTime() : -1);
		dest.writeByte(this.flashDeal ? (byte) 1 : (byte) 0);
		dest.writeByte(this.lastMinute ? (byte) 1 : (byte) 0);
	}

}
