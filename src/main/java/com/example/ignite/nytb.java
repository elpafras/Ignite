package com.example.ignite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class nytb implements Parcelable {

    String key, title, bait, koor, nada, no;

    public nytb() {
    }

    protected nytb(Parcel in) {
        key = in.readString();
        title = in.readString();
        bait = in.readString();
        koor = in.readString();
        nada = in.readString();
        no = in.readString();
    }

    public static final Creator<nytb> CREATOR = new Creator<nytb>() {
        @Override
        public nytb createFromParcel(Parcel in) {
            return new nytb(in);
        }

        @Override
        public nytb[] newArray(int size) {
            return new nytb[size];
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

    public String getBait() {
        return bait;
    }

    public void setBait(String bait) {
        this.bait = bait;
    }

    public String getKoor() {
        return koor;
    }

    public void setKoor(String koor) {
        this.koor = koor;
    }

    public String getNada() {
        return nada;
    }

    public void setNada(String nada) {
        this.nada = nada;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public nytb(String key, String title, String bait, String koor, String nada, String no) {
        this.key = key;
        this.title = title;
        this.bait = bait;
        this.koor = koor;
        this.nada = nada;
        this.no = no;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(title);
        parcel.writeString(bait);
        parcel.writeString(koor);
        parcel.writeString(nada);
        parcel.writeString(no);
    }
}
