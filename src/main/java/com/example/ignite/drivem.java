package com.example.ignite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class drivem implements Parcelable {
    String key, link, title;

    public drivem() {
    }

    public drivem(String key, String link, String title) {
        this.key = key;
        this.link = link;
        this.title = title;
    }

    protected drivem(Parcel in) {
        key = in.readString();
        link = in.readString();
        title = in.readString();
    }

    public static final Creator<drivem> CREATOR = new Creator<drivem>() {
        @Override
        public drivem createFromParcel(Parcel in) {
            return new drivem(in);
        }

        @Override
        public drivem[] newArray(int size) {
            return new drivem[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(link);
        parcel.writeString(title);
    }


}
