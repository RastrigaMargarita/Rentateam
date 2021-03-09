package com.example.rentateam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentateam.jsonModel.JsonUser;
import com.example.rentateam.ui.user.UserFragment;

public class UserActivity extends AppCompatActivity {
    private JsonUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, UserFragment.newInstance())
                    .commitNow();
        }
        currentUser = getIntent().getParcelableExtra("item");

    }

    public JsonUser getCurrentUser() {
        return currentUser;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}