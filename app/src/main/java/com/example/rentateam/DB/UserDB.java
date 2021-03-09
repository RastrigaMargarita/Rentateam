package com.example.rentateam.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rentateam.jsonModel.JsonUser;

@Database(entities = {JsonUser.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDao userDao();

}

