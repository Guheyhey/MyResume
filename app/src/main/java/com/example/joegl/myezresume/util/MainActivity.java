package com.example.joegl.myezresume.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.joegl.myezresume.R;
import com.example.joegl.myezresume.model.BasicInfo;
import com.example.joegl.myezresume.model.Education;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private Education education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fakeData();
        setupUI();

    }

    private void setupUI() {
        setContentView(R.layout.activity_main);
        setBasicInfo();
        setEducationInfo();
    }

    private void setBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }

    private void setEducationInfo() {
        ((TextView) findViewById(R.id.education_school)).setText(education.school +
                " (" + DateUtil.dateToString(education.startDate) + "~" +
                DateUtil.dateToString(education.endDate) + ") \n" + "Major : " + education.major);
        ((TextView) findViewById(R.id.education_courses)).setText(formatItem(education.courses));

    }

    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.name = "Joe Liao";
        basicInfo.email = "joeglancith@hotmail.com";

        education = new Education();
        education.school = "MTU";
        education.major = "Electrical Engineering";
        education.startDate = DateUtil.stringToDate("09/2015");
        education.endDate = DateUtil.stringToDate("12/2016");
        education.courses = new ArrayList<>();
        education.courses.add("Data Structure");
        education.courses.add("Algorithms");
        education.courses.add("Wireless Sensor Network");

    }

    public static String formatItem(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(" - ").append(item).append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
