package com.example.notesappjava.manager;

import com.example.notesappjava.model.Note;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {
    public static final String TAG = RealmManager.class.getSimpleName();
    private static RealmManager realmManager;
    private static Realm realm;

    public static RealmManager getInstance() {
        if (realmManager == null)
            realmManager = new RealmManager();
        return realmManager;
    }

    public RealmManager() {
        // Get Default Realm
        realm = Realm.getDefaultInstance();
    }

    public void saveNote(Note note) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    public ArrayList<Note> loadNotes() {
        RealmResults<Note> results = realm.where(Note.class).findAll();
        return new ArrayList<Note>(results);
    }

}
