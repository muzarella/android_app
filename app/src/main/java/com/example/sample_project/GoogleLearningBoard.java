package com.example.sample_project;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

public class GoogleLearningBoard  implements Parcelable {
        private String name;
        private  int value ; // value returns both hour and score
        private String country ;
        private String badgeUrl ;

    public GoogleLearningBoard(String _name, int _value, String _country, String _badgeUrl   ){
        this.name = _name ;
        this.value = _value ;
        this.country = _country ;
        this.badgeUrl = _badgeUrl ;

    }

    private GoogleLearningBoard(Parcel parcel) {
        name = parcel.readString() ;
        value = parcel.readInt();
        country = parcel.readString();
        badgeUrl = parcel.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(value);
        dest.writeString(country);
        dest.writeString(badgeUrl);
    }

    public static final Parcelable.Creator<GoogleLearningBoard> CREATOR =
            new Parcelable.Creator<GoogleLearningBoard>() {
                @Override
                public GoogleLearningBoard createFromParcel(Parcel parcel) {
                    return new GoogleLearningBoard(parcel);
                }

                @Override
                public GoogleLearningBoard[] newArray(int size) {
                    return new GoogleLearningBoard[size];
                }
            };

}
