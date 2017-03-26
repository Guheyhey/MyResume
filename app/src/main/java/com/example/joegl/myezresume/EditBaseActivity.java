package com.example.joegl.myezresume;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by joegl on 2017/3/26.
 */

public abstract class EditBaseActivity<T> extends AppCompatActivity{
    private T data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        // 添加返回按键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = initializeData();
        if (data != null) {
            setupUIForEdit(data);
        } else {
            setupUIForCreate();
        }
    }

    // 添加保存按键
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    // 设置 ActionBar 选中事件


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.ic_save) {
            saveAndExit(data);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayoutID();

    protected abstract void setupUIForCreate();

    protected abstract void setupUIForEdit(@NonNull T data);

    protected abstract void saveAndExit(@NonNull T data);

    protected abstract T initializeData();
}
