package com.example.notesappjava.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.notesappjava.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNote(Note note);

    @Query("select * from note_table")
    List<Note> getNotes();

    @Query("update note_table set isRead=:isRead where id=:id")
    void updateNote(int id, boolean isRead);
}
