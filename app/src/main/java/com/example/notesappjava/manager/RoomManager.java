package com.example.notesappjava.manager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesappjava.database.NoteDao;
import com.example.notesappjava.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class RoomManager extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static RoomManager INSTANCE;

    public static RoomManager getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (RoomManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomManager.class, "note_db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
