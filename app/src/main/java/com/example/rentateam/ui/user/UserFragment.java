package com.example.rentateam.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rentateam.UserActivity;
import com.example.rentateam.databinding.FragmentUserDetailBinding;

public class UserFragment extends Fragment {

    private FragmentUserDetailBinding ui;

    public static UserFragment newInstance() {

        return new UserFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ui = FragmentUserDetailBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserViewModel mViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mViewModel.setCurrentUser(((UserActivity) getActivity()).getCurrentUser());
        mViewModel.getCurrentUser().observe(getViewLifecycleOwner(),
                s -> {
                    ui.userDetailName.setText(s.getFirstName());
                    ui.userDetailSname.setText(s.getLastName());
                    ui.userEmail.setText(s.getEmail());
                });

        mViewModel.loadImage(ui.userDetailImage);
    }
}