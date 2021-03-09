package com.example.rentateam.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rentateam.App;
import com.example.rentateam.DB.UserDao;
import com.example.rentateam.jsonModel.JsonGetter;
import com.example.rentateam.jsonModel.JsonUser;
import com.example.rentateam.jsonModel.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends ViewModel {

    private final MutableLiveData<JsonUser[]> userListArray;
    private final MutableLiveData<String> gettingDataError;
    private final UserDao userDao;

    public UserListViewModel() {
        userListArray = new MutableLiveData<>();
        gettingDataError = new MutableLiveData<>();
        userDao = App.getUserDao();
        GetUserList();
    }

    public LiveData<JsonUser[]> getUserList() {
        return userListArray;
    }

    public LiveData<String> getError() {
        return gettingDataError;
    }

    private void GetUserList() {

        JsonGetter apiholder = App.getApiHolder();
        apiholder.getUsers().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body() != null) {
                    userListArray.setValue(response.body().getData());
                    saveToBD(response.body().getData());
                } else {
                    gettingDataError.setValue(response.message());
                    userListArray.setValue(loadFromDB());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                gettingDataError.setValue("");
                userListArray.setValue(loadFromDB());
            }
        });

    }

    private void saveToBD(JsonUser[] data) {
        userDao.deleteAll();
        for (JsonUser jsonUser : data
        ) {
            userDao.insert(jsonUser);
        }

    }

    private JsonUser[] loadFromDB() {
        return userDao.getAll();
    }


}