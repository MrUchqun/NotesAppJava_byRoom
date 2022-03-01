package com.example.notesappjava.model;

import androidx.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {

    @PrimaryKey
    private String id;
    private String text;
    private String date;
    public boolean isRead = false;

    public Note() {
    }

    public Note(String id, String date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public Note(String id, String text, String date, boolean isRead) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.isRead = isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        this.isRead = read;
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
