package com.example.ignite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ytm implements Parcelable  {
    String key, title, yturl, image;

    public ytm() {
    }

    protected ytm(Parcel in) {
        key = in.readString();
        title = in.readString();
        yturl = in.readString();
        image = in.readString();
    }

    public static final Creator<ytm> CREATOR = new Creator<ytm>() {
        @Override
        public ytm createFromParcel(Parcel in) {
            return new ytm(in);
        }

        @Override
        public ytm[] newArray(int size) {
            return new ytm[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYturl() {
        return yturl;
    }

    public void setYturl(String yturl) {
        this.yturl = yturl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ytm(String key, String title, String yturl, String image) {
        this.key = key;
        this.title = title;
        this.yturl = yturl;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(title);
        parcel.writeString(yturl);
        parcel.writeString(image);
    }
}
