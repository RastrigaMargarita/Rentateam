package com.example.rentateam.ui.user;

import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rentateam.jsonModel.JsonUser;
import com.squareup.picasso.Picasso;

public class UserViewModel extends ViewModel {
    private JsonUser currentUserObject;
    private final MutableLiveData<JsonUser> currentUser;

    public UserViewModel() {
        this.currentUser = new MutableLiveData<>();
    }

    public void setCurrentUser(JsonUser s) {
        currentUserObject = s;
        currentUser.setValue(s);
    }

    public MutableLiveData<JsonUser> getCurrentUser() {
        return currentUser;
    }

    public void loadImage(ImageView view) {
        Picasso.get()
                .load(currentUserObject.getAvatar())
                .placeholder(android.R.drawable.picture_frame)
                .into(view);
    }

}