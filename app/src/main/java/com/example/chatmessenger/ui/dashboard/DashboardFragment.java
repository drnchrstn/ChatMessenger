package com.example.chatmessenger.ui.dashboard;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatmessenger.R;

public class DashboardFragment extends Fragment

    implements LoaderManager.LoaderCallbacks<Cursor>

{
        private RecyclerView ContactList;
        private LinearLayoutManager mLinearLayout;
        ContactAdapter contactAdapter;
        private int datalimit = 15;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ContactList = v.findViewById(R.id.ContactList);
        mLinearLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        getLoaderManager().initLoader(1, null, this);
        ContactList.setLayoutManager(mLinearLayout);
        contactAdapter = new ContactAdapter();
        ContactList.setAdapter(contactAdapter);




        return v;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == 1){
            return contactsLoader();
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        contactAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        contactAdapter.swapCursor(null);
    }


    private Loader<Cursor> contactsLoader(){
        Uri uri = ContactProvider.CONTENT_URI;
        String[] projection = {
                ContactDb.ROW_ID,
                ContactDb.ROW_USERNAME,
                ContactDb.ROW_DATE
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = "Id DESC LIMIT " + datalimit;

        return new CursorLoader(
                getActivity(),
                uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }
}