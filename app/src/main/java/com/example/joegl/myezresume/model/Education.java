package com.example.joegl.myezresume.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.joegl.myezresume.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by joegl on 2017/3/24.
 */

public class Education implements Parcelable{

    public String id;

    public String school;

    public String major;

    public Date startDate;

    public Date endDate;

    public List<String> courses;

    public Education() {
        id = UUID.randomUUID().toString();
    }

    protected Education (Parcel in) {
        id = in.readString();
        school = in.readString();
        major = in.readString();
        startDate = DateUtil.stringToDate(in.readString());
        endDate = DateUtil.stringToDate(in.readString());
        courses = in.createStringArrayList();
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(school);
        dest.writeString(major);
        dest.writeString(DateUtil.dateToString(startDate));
        dest.writeString(DateUtil.dateToString(endDate));
        dest.writeStringList(courses);
    }
}
