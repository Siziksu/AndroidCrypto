package com.siziksu.crypto.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoinDomainModel implements Parcelable {

    public int id;
    public String name;
    public String symbol;
    public String logo;
    public Integer rank;
    public String priceUsd;
    public String priceBtc;
    public long volumeUsd24h;
    public long marketCapUsd;
    public long availableSupply;
    public long totalSupply;
    public String percentChange1h;
    public String percentChange24h;
    public String percentChange7d;
    public String createdAt;
    public String updatedAt;

    public CoinDomainModel() {}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.symbol);
        dest.writeString(this.logo);
        dest.writeValue(this.rank);
        dest.writeString(this.priceUsd);
        dest.writeString(this.priceBtc);
        dest.writeLong(this.volumeUsd24h);
        dest.writeLong(this.marketCapUsd);
        dest.writeLong(this.availableSupply);
        dest.writeLong(this.totalSupply);
        dest.writeString(this.percentChange1h);
        dest.writeString(this.percentChange24h);
        dest.writeString(this.percentChange7d);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    protected CoinDomainModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.symbol = in.readString();
        this.logo = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.priceUsd = in.readString();
        this.priceBtc = in.readString();
        this.volumeUsd24h = in.readLong();
        this.marketCapUsd = in.readLong();
        this.availableSupply = in.readLong();
        this.totalSupply = in.readLong();
        this.percentChange1h = in.readString();
        this.percentChange24h = in.readString();
        this.percentChange7d = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<CoinDomainModel> CREATOR = new Creator<CoinDomainModel>() {
        @Override
        public CoinDomainModel createFromParcel(Parcel source) {return new CoinDomainModel(source);}

        @Override
        public CoinDomainModel[] newArray(int size) {return new CoinDomainModel[size];}
    };
}
