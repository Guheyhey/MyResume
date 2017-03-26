package com.example.joegl.myezresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.joegl.myezresume.R;
import com.example.joegl.myezresume.model.Education;
import com.example.joegl.myezresume.util.DateUtil;

import java.util.Arrays;

/**
 * Created by joegl on 2017/3/24.
 */

public class EducationEditActivity extends EditBaseActivity<Education> {
    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION__ID = "education_id";
    

    @Override
    protected int getLayoutID() {
        return R.layout.activity_education_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.education_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull Education data) {
        ((EditText) findViewById(R.id.education_edit_school))
                .setText(data.school);
        ((EditText) findViewById(R.id.education_edit_major))
                .setText(data.major);
        ((EditText) findViewById(R.id.education_edit_start_date))
                .setText(DateUtil.dateToString(data.startDate));
        ((EditText) findViewById(R.id.education_edit_end_date))
                .setText(DateUtil.dateToString(data.endDate));
        ((EditText) findViewById(R.id.education_edit_courses))
                .setText(TextUtils.join("\n", data.courses));
    }

    @Override
    protected void saveAndExit(@NonNull Education data) {
        if (data == null) {
            data = new Education();
        }
        data.school = ((EditText) findViewById(R.id.education_edit_school))
                .getText().toString();
        data.major = ((EditText) findViewById(R.id.education_edit_major))
                .getText().toString();
        data.startDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.education_edit_start_date)).getText().toString());
        data.endDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.education_edit_end_date)).getText().toString());
        data.courses = Arrays.asList(TextUtils.split(((EditText)
                findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    protected Education initializeData() {
        return getIntent().getParcelableExtra(KEY_EDUCATION);
    }
    




}
