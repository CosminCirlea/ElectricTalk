package com.example.electrictalk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electrictalk.Activities.MessagesActivity;
import com.example.electrictalk.Activities.TopicsActivity;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.TopicModel;
import com.example.electrictalk.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {
    private List<TopicModel> myTopics;
    private Context context;

    public TopicsAdapter(List<TopicModel> myTopics, Context context) {
        this.myTopics = myTopics;
        this.context = context;
    }

    @NonNull
    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_topics, parent, false);
        TopicsAdapter.ViewHolder holder = new TopicsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsAdapter.ViewHolder holder, int position) {

        String topic = myTopics.get(position).getTitle();
        String content = myTopics.get(position).getContent();

        holder.topicTv.setText(topic);
        holder.topicTv.setText(content);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, MessagesActivity.class);
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTopics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView topicTv, contentTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View itemView) {
            topicTv = itemView.findViewById(R.id.tv_topic);
            contentTv = itemView.findViewById(R.id.tv_topic_content);
        }
    }
}
