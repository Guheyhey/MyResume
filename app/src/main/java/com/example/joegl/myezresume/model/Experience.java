package com.example.joegl.myezresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.joegl.myezresume.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by joegl on 2017/3/26.
 */

public class Experience implements Parcelable {

    public String id;
    public String companyName;
    public String title;
    public Date startDate;
    public Date endDate;
    public List<String> details;

    public Experience() {
        id = UUID.randomUUID().toString();
    }

    protected Experience(Parcel in) {
        id = in.readString();
        companyName = in.readString();
        title = in.readString();
        startDate = DateUtil.stringToDate(in.readString());
        endDate = DateUtil.stringToDate(in.readString());
        details = in.createStringArrayList();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(companyName);
        dest.writeString(title);
        dest.writeString(DateUtil.dateToString(startDate));
        dest.writeString(DateUtil.dateToString(endDate));
        dest.writeStringList(details);
    }
}
