package com.example.joegl.myezresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.joegl.myezresume.model.Experience;
import com.example.joegl.myezresume.util.DateUtil;

import java.util.Arrays;

/**
 * Created by joegl on 2017/3/27.
 */

public class ExperienceEditActivity extends EditBaseActivity<Experience> {
    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_ID = "experience_id";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_experience_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.experience_delete_btn).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Experience data) {
        ((EditText) findViewById(R.id.experience_edit_company))
                .setText(data.companyName);
        ((EditText) findViewById(R.id.experience_edit_title))
                .setText(data.title);
        ((EditText) findViewById(R.id.experience_edit_start_date))
                .setText(DateUtil.dateToString(data.startDate));
        ((EditText) findViewById(R.id.experience_edit_end_date))
                .setText(DateUtil.dateToString(data.endDate));
        ((EditText) findViewById(R.id.experience_edit_description))
                .setText(TextUtils.join("\n", data.details));

        findViewById(R.id.experience_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EXPERIENCE_ID, data.id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@NonNull Experience data) {
        if (data == null) {
            data = new Experience();
        }
        data.companyName = ((EditText) findViewById(R.id.experience_edit_company)).getText().toString();
        data.title = ((EditText) findViewById(R.id.experience_edit_title)).getText().toString();
        data.startDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.experience_edit_start_date)).getText().toString());
        data.endDate = DateUtil.stringToDate(((EditText)
                findViewById(R.id.experience_edit_end_date)).getText().toString());
        data.details = Arrays.asList(TextUtils.split(
                ((EditText) findViewById(R.id.experience_edit_description)).getText().toString(), "\n"));

        Intent intent = new Intent();
        intent.putExtra(KEY_EXPERIENCE, data);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected Experience initializeData() {
        return getIntent().getParcelableExtra(KEY_EXPERIENCE);
    }
}
