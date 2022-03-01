package com.example.notesappjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappjava.R;
import com.example.notesappjava.manager.RealmManager;
import com.example.notesappjava.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Note> notes;
    RealmManager realmManager = RealmManager.getInstance();

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addNote(Note note) {
        notes.add(note);
        realmManager.saveNote(note);
        notifyDataSetChanged();
    }

    public void changeNote(Note note) {
        notes.remove(note);
        addNote(note);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = notes.get(position);
        if (holder instanceof NoteViewHolder) {
            ((NoteViewHolder) holder).tvDate.setText(note.getDate());
            ((NoteViewHolder) holder).tvText.setText(note.getText());

//            if (note.isRead())
//                ((NoteViewHolder) holder).ivPoint.setVisibility(View.INVISIBLE);
//
//            ((NoteViewHolder) holder).tvText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    note.setRead(true);
//                    changeNote(note);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvText;
        ImageView ivPoint;

        public NoteViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tv_date);
            tvText = view.findViewById(R.id.tv_text);
            ivPoint = view.findViewById(R.id.iv_point);
        }
    }
}
