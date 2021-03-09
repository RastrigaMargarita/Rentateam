package com.example.rentateam.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rentateam.jsonModel.JsonUser;

@Dao
public interface UserDao {
    @Query("SELECT * FROM jsonUser")
    JsonUser[] getAll();

    @Query("SELECT * FROM jsonUser WHERE id = :id")
    LiveData<JsonUser> getById(long id);

    @Query("DELETE FROM jsonUser")
    void deleteAll();

    @Insert
    void insert(JsonUser user);

}

