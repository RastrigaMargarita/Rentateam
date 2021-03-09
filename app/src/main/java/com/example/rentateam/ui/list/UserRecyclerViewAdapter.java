package com.example.rentateam.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rentateam.R;
import com.example.rentateam.databinding.FragmentUserCardBinding;
import com.example.rentateam.jsonModel.JsonUser;


public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private JsonUser[] mValues;
    private OnItemClickListener itemClickListener;

    public UserRecyclerViewAdapter(JsonUser[] items) {
        mValues = items;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, JsonUser position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setmValues(JsonUser[] mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues[position];
        holder.mIdView.setText(mValues[position].getFirstName());
        holder.mContentView.setText(mValues[position].getLastName());
    }

    @Override
    public int getItemCount() {

        return mValues == null ? 0 : mValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public JsonUser mItem;
        public final FragmentUserCardBinding ui;


        public ViewHolder(View view) {
            super(view);
            ui = FragmentUserCardBinding.bind(view);
            mView = view;
            mIdView = ui.userName;
            mContentView = ui.userFamily;
            ui.CardLayout.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, mItem);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}