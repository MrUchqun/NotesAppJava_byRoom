package com.example.notesappjava.database;

import android.app.Application;

import com.example.notesappjava.manager.RoomManager;
import com.example.notesappjava.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Application application) {
        RoomManager db = RoomManager.getDatabase(application);
        noteDao = db.noteDao();
    }

    public List<Note> getNotes() {
        return noteDao.getNotes();
    }

    public void saveNote(Note note) {
        noteDao.saveNote(note);
    }

    public void updateNote(int id, boolean isRead) {
        noteDao.updateNote(id, isRead);
    }
}
