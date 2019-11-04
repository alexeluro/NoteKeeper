package com.example.notekeeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notekeeper.Notes.NoteDetails;
import com.example.notekeeper.R;
import com.example.notekeeper.Repository.LocalDatabase.DbHandler;
import com.example.notekeeper.Repository.RepositoryHandler;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<NoteDetails> Notes = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<NoteDetails> notes) {
        this.context = context;
        Notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewer = LayoutInflater.from(context).inflate(R.layout.custom_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(viewer);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.topic.setText(Notes.get(position).getNoteTitle(position));
        holder.subTopic.setText(Notes.get(position).getNoteContent(position));
    }

    @Override
    public int getItemCount() {
        return Notes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView topic, subTopic;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            subTopic = itemView.findViewById(R.id.sub_topic);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
