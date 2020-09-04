package com.example.finalexam;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder>  {

    private Context context;
    private List<Data> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView image;
        public TextView dot;


        public MyViewHolder(View view) {
            super(view);
            image=view.findViewById(R.id.picR);
            dot = view.findViewById(R.id.dot);

        }
    }


    public TaskAdapter(Context context, List<Data> userList) {
        this.context = context;
        this.taskList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data user = taskList.get(position);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(usr.getProfile(), 0, usr.getProfile().length);
        holder.dot.setText(Html.fromHtml("&#8226;"));
        holder.image.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }




}
