package com.example.notesappjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.notesappjava.R;
import com.example.notesappjava.adapter.NoteAdapter;
import com.example.notesappjava.database.NoteRepository;
import com.example.notesappjava.fragment.NoteFragment;
import com.example.notesappjava.manager.RoomManager;
import com.example.notesappjava.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        noteRepository = new NoteRepository(getApplication());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        noteAdapter = new NoteAdapter(this, getNotes());
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialog();
            }
        });
    }

    private void callDialog() {
        NoteFragment noteFragment = new NoteFragment(noteAdapter);
        noteFragment.show(getSupportFragmentManager(), null);
    }

    private List<Note> getNotes() {
        return noteRepository.getNotes();
    }
}