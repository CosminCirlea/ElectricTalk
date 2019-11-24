package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.electrictalk.Adapters.CarsAdapter;
import com.example.electrictalk.Adapters.CategoriesAdapter;
import com.example.electrictalk.Adapters.TopicsAdapter;
import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CategoryModel;
import com.example.electrictalk.Models.TopicModel;
import com.example.electrictalk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessagesActivity extends AppCompatActivity {
    private TopicsAdapter topicsAdapter;
    private RecyclerView recyclerView;
    private List<TopicModel> topicModels;
    private String id, title, content;
    private FloatingActionButton addTopicsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        recyclerView = findViewById(R.id.rv_listed_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        addTopicsBtn = findViewById(R.id.btn_add_topics);
        id = getIntent().getStringExtra("id");

        addTopicsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, AddMessageActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        topicModels = new ArrayList<>();
        HttpClientManager.getInstance().getTopics(UUID.fromString(id), new HttpClientManager.OnDataReceived<List<TopicModel>>() {
            @Override
            public void dataReceived(List<TopicModel> data) {
                topicModels = data;
            }

            @Override
            public void onFailed() {

            }
        });
        populateRecycler();
    }

    private void populateRecycler()
    {
        topicsAdapter = new TopicsAdapter(topicModels, this);
        recyclerView.setAdapter(topicsAdapter);
    }
}
