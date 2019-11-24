package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CategoryModel;
import com.example.electrictalk.R;

public class AddCategoryActivity extends BaseAppCompat {

    private EditText titleEt;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initializeViews();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEt.getText().toString();
                HttpClientManager.getInstance().addCategory(title, new HttpClientManager.OnDataReceived<CategoryModel>() {
                    @Override
                    public void dataReceived(CategoryModel data) {
                        StorageHelper.myCategoriesList.add(data);
                        finish();
                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        });
    }

    private void initializeViews()
    {
        titleEt = findViewById(R.id.et_category_title);
        addBtn = findViewById(R.id.btn_add_category1);
    }
}
