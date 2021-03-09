package com.example.rentateam;

import android.app.Application;

import androidx.room.Room;

import com.example.rentateam.DB.UserDB;
import com.example.rentateam.DB.UserDao;
import com.example.rentateam.jsonModel.JsonGetter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static JsonGetter apiHolder;
    private static UserDB userDB;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.urlToServer))
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();
        try {
            apiHolder = retrofit.create(JsonGetter.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDB = Room.databaseBuilder(this, UserDB.class, "user")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static JsonGetter getApiHolder() {
        return apiHolder;
    }

    private Gson gson() {
        return new GsonBuilder().
                setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    public static UserDao getUserDao() {
        return userDB.userDao();
    }

}
