package com.example.joegl.myezresume;


/**
 * Created by joegl on 2017/3/26.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import com.example.joegl.myezresume.model.Project;
import com.example.joegl.myezresume.util.DateUtil;

import java.util.Arrays;

@SuppressWarnings("ConstantConditions")
public class ProjectEditActivity extends EditBaseActivity<Project> {

    public static final String KEY_PROJECT = "project";
    public static final String KEY_PROJECT_ID = "project_id";



    @Override
    protected int getLayoutID() {
        return R.layout.activity_project_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.project_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Project data) {
        ((EditText) findViewById(R.id.project_edit_title))
                .setText(data.projectName);
        ((EditText) findViewById(R.id.project_edit_start_date))
                .setText(DateUtil.dateToString(data.startDate));
        ((EditText) findViewById(R.id.project_edit_end_date))
                .setText(DateUtil.dateToString(data.endDate));
        ((EditText) findViewById(R.id.project_edit_description))
                .setText(TextUtils.join("\n", data.details));
        findViewById(R.id.project_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_PROJECT_ID, data.id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Project data) {
        if (data == null) {
            data = new Project();
        }

        data.projectName = ((EditText) findViewById(R.id.project_edit_title)).getText().toString();
        data.startDate = DateUtil.stringToDate(((EditText) findViewById(R.id.project_edit_start_date)).getText().toString());
        data.endDate = DateUtil.stringToDate(((EditText) findViewById(R.id.project_edit_end_date)).getText().toString());
        data.details = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.project_edit_description)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_PROJECT, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected Project initializeData() {
        return getIntent().getParcelableExtra(KEY_PROJECT);
    }
}