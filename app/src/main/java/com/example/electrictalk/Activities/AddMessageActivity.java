package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.TopicModel;
import com.example.electrictalk.R;

import java.util.UUID;

public class AddMessageActivity extends BaseAppCompat {

    private Button addTopicBtn;
    private EditText titleEt, contentEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        addTopicBtn = findViewById(R.id.btn_add_message);
        titleEt = findViewById(R.id.et_autor);
        contentEt = findViewById(R.id.et_mesaj);

        final String id = getIntent().getStringExtra("id");

        addTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClientManager.getInstance().addTopic(UUID.fromString(id), titleEt.getText().toString(), contentEt.getText().toString(), new HttpClientManager.OnDataReceived<TopicModel>() {
                    @Override
                    public void dataReceived(TopicModel data) {
                        finish();
                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        });
    }
}
