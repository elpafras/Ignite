package com.example.ignite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class model implements Parcelable {

    String key, title, ayat, content;

    public model() {
    }

    public model(String key, String title, String ayat, String content) {
        this.key = key;
        this.title = title;
        this.ayat = ayat;
        this.content = content;
    }



    protected model(Parcel in) {
        key = in.readString();
        title = in.readString();
        ayat = in.readString();
        content = in.readString();
    }

    public static final Creator<model> CREATOR = new Creator<model>() {
        @Override
        public model createFromParcel(Parcel in) {
            return new model(in);
        }

        @Override
        public model[] newArray(int size) {
            return new model[size];
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

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(title);
        parcel.writeString(ayat);
        parcel.writeString(content);
    }
}
