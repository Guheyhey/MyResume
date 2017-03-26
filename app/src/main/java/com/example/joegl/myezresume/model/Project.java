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

public class Project implements Parcelable {

    public String id;
    public String projectName;
    public Date startDate;
    public Date endDate;
    public List<String> details;

    public Project() {
        id = UUID.randomUUID().toString();
    }

    protected Project(Parcel in) {
        id = in.readString();
        projectName = in.readString();
        startDate = DateUtil.stringToDate(in.readString());
        endDate = DateUtil.stringToDate(in.readString());
        details = in.createStringArrayList();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(projectName);
        dest.writeString(DateUtil.dateToString(startDate));
        dest.writeString(DateUtil.dateToString(endDate));
        dest.writeStringList(details);
    }
}
