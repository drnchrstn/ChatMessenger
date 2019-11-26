package com.example.chatmessenger.ui.dashboard;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmessenger.BaseCursorAdapter;
import com.example.chatmessenger.R;

public class ContactAdapter extends BaseCursorAdapter<ContactAdapter.ViewHolder> {

    Context context;




    public ContactAdapter() {
        super(null);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        String id = cursor.getString(0);
        String name = cursor.getString(1);
        String date = cursor.getString(2);

        holder.TxtName.setText(name);
        holder.TxtDate.setText(date);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactcard, parent, false);



        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView TxtName, TxtDate;


        public ViewHolder(@NonNull View v) {
            super(v);

            TxtName = v.findViewById(R.id.TxtName);
            TxtDate = v.findViewById(R.id.TxtDate);

        }
    }
}
