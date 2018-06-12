package com.siziksu.crypto.presenter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoinHistorical implements Parcelable {

    public String priceUsd;
    public String snapshotAt;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.priceUsd);
        dest.writeString(this.snapshotAt);
    }

    public CoinHistorical() {}

    protected CoinHistorical(Parcel in) {
        this.priceUsd = in.readString();
        this.snapshotAt = in.readString();
    }

    public static final Parcelable.Creator<CoinHistorical> CREATOR = new Parcelable.Creator<CoinHistorical>() {
        @Override
        public CoinHistorical createFromParcel(Parcel source) {return new CoinHistorical(source);}

        @Override
        public CoinHistorical[] newArray(int size) {return new CoinHistorical[size];}
    };
}
