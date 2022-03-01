package com.example.notesappjava.database;

import android.app.Application;

import com.example.notesappjava.manager.RoomManager;
import com.example.notesappjava.model.User;

import java.util.List;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        RoomManager db = RoomManager.getDatabase(application);
        userDao = db.userDao();
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
