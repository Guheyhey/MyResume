package com.example.joegl.myezresume.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joegl.myezresume.R;
import com.example.joegl.myezresume.model.BasicInfo;
import com.example.joegl.myezresume.model.Education;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private List<Education> educations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fakeData();
        setupUI();

    }

    private void setupUI() {
        setContentView(R.layout.activity_main);
        setBasicInfo();
        setupEducations();
    }

    private void setBasicInfo() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }

//    private void setEducationInfo() {
//        ((TextView) findViewById(R.id.education_school)).setText(education.school +
//                " (" + DateUtil.dateToString(education.startDate) + "~" +
//                DateUtil.dateToString(education.endDate) + ") \n" + "Major : " + education.major);
//        ((TextView) findViewById(R.id.education_courses)).setText(formatItem(education.courses));
//
//    }

    private void setupEducations() {
        LinearLayout educationsLayout = (LinearLayout) findViewById(R.id.education_list);
        educationsLayout.removeAllViews();
        for (Education education : educations) {
            View educationView = getLayoutInflater().inflate(R.layout.education_item, null);
            setupEducation(educationView, education);
            educationsLayout.addView(educationView);
        }
    }

    private void setupEducation(View educationView, final Education education) {
        String dateString = DateUtil.dateToString(education.startDate)
                + " ~ " + DateUtil.dateToString(education.endDate);
        ((TextView) educationView.findViewById(R.id.education_school)).setText(education.school +
                " (" + dateString + ") \n" + "Major : " + education.major);
        ((TextView) educationView.findViewById(R.id.education_courses)).setText(formatItem(education.courses));
    }

    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.name = "Joe Liao";
        basicInfo.email = "joeglancith@hotmail.com";

        Education education1 = new Education();
        education1.school = "MTU";
        education1.major = "Electrical Engineering";
        education1.startDate = DateUtil.stringToDate("09/2015");
        education1.endDate = DateUtil.stringToDate("12/2016");
        education1.courses = new ArrayList<>();
        education1.courses.add("Data Structure");
        education1.courses.add("Algorithms");
        education1.courses.add("Wireless Sensor Network");

        Education education2 = new Education();
        education2.school = "SHU";
        education2.major = "Communication Engineering";
        education2.startDate = DateUtil.stringToDate("09/2010");
        education2.endDate = DateUtil.stringToDate("07/2014");
        education2.courses = new ArrayList<>();
        education2.courses.add("C++");
        education2.courses.add("DataBase");

        educations = new ArrayList<>();
        educations.add(education1);
        educations.add(education2);

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
