package com.example.notesappjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notesappjava.R;
import com.example.notesappjava.adapter.NoteAdapter;
import com.example.notesappjava.database.UserRepository;
import com.example.notesappjava.fragment.NoteFragment;
import com.example.notesappjava.manager.RealmManager;
import com.example.notesappjava.model.Note;
import com.example.notesappjava.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private RealmManager realmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        realmManager = RealmManager.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        noteAdapter = new NoteAdapter(this, getNotes());
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                callDialog();
                roomDatabase();
            }
        });
    }

    void roomDatabase() {
        UserRepository repository = new UserRepository(getApplication());
        User user = new User(2, "Bekhruzbek");
//        repository.saveUser(user);
        new UserAsyncTask(repository).execute(user);
    }

    private void callDialog() {
        NoteFragment noteFragment = new NoteFragment(noteAdapter);
        noteFragment.show(getSupportFragmentManager(), null);
    }

    private ArrayList<Note> getNotes() {
        return realmManager.loadNotes();
    }


    class UserAsyncTask extends AsyncTask<User, Void, List<User>> {

        UserRepository repository;

        UserAsyncTask(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        protected List<User> doInBackground(User... users) {
            repository.saveUser(users[0]);
            return repository.getUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            Log.d("@@@", "onPostExecute: " + users.size());
        }
    }

}