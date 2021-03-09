package com.example.rentateam.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentateam.R;
import com.example.rentateam.UserActivity;
import com.example.rentateam.databinding.FragmentListBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UserListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentListBinding ui =
                FragmentListBinding.inflate(inflater, container, false);

        UserListViewModel userListViewModel =
                new ViewModelProvider(this).get(UserListViewModel.class);


        ProgressBar progressBar = ui.progressBar;
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerView = ui.list;
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter(null);
        userRecyclerViewAdapter.SetOnItemClickListener((view, position) -> {
            Intent intent = new Intent(getActivity(), UserActivity.class);
            intent.putExtra("item", position);
            startActivity(intent);
            getActivity().finish();
        });
        recyclerView.setAdapter(userRecyclerViewAdapter);

        userListViewModel.getUserList().observe(getViewLifecycleOwner(),
                s -> {

                    userRecyclerViewAdapter.setmValues(s);
                    recyclerView.getAdapter().notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                });

        userListViewModel.getError().observe(getViewLifecycleOwner(),
                s -> Snackbar.make(container,
                        getResources().getString(R.string.gettin_data_error) + s,
                        BaseTransientBottomBar.LENGTH_LONG).show());

        return ui.getRoot();

    }
}