package com.example.joegl.myezresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.joegl.myezresume.R;
import com.example.joegl.myezresume.model.Education;
import com.example.joegl.myezresume.util.DateUtil;

import java.util.Arrays;

/**
 * Created by joegl on 2017/3/24.
 */

public class EducationEditActivity extends AppCompatActivity {
    public static final String KEY_EDUCATION = "education";
    private Education education;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())   {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        if (education == null) {
            education = new Education();
        }
        education.school = ((EditText) findViewById(R.id.education_edit_school))
                .getText().toString();
        education.major = ((EditText) findViewById(R.id.education_edit_major))
                .getText().toString();
        education.startDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.education_edit_start_date)).getText().toString());
        education.endDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.education_edit_end_date)).getText().toString());
        education.courses = Arrays.asList(TextUtils.split(((EditText)
                findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, education);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }
}
