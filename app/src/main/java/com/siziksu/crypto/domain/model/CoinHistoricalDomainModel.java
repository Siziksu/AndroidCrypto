package com.siziksu.crypto.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoinHistoricalDomainModel implements Parcelable {

    public String priceUsd;
    public String snapshotAt;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.priceUsd);
        dest.writeString(this.snapshotAt);
    }

    public CoinHistoricalDomainModel() {}

    protected CoinHistoricalDomainModel(Parcel in) {
        this.priceUsd = in.readString();
        this.snapshotAt = in.readString();
    }

    public static final Creator<CoinHistoricalDomainModel> CREATOR = new Creator<CoinHistoricalDomainModel>() {
        @Override
        public CoinHistoricalDomainModel createFromParcel(Parcel source) {return new CoinHistoricalDomainModel(source);}

        @Override
        public CoinHistoricalDomainModel[] newArray(int size) {return new CoinHistoricalDomainModel[size];}
    };
}
