package com.example.joegl.myezresume.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joegl on 2017/3/24.
 */

public class BasicInfo implements Parcelable{

    public String name;

    public String email;

    public Uri imageUri;

    public BasicInfo() {};

    protected BasicInfo(Parcel in) {
        name = in.readString();
        email = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<BasicInfo> CREATOR = new Creator<BasicInfo>() {
        @Override
        public BasicInfo createFromParcel(Parcel in) {
            return new BasicInfo(in);
        }

        @Override
        public BasicInfo[] newArray(int size) {
            return new BasicInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeParcelable(imageUri, flags);
    }
}
