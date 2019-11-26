package com.example.chatmessenger.ui.dashboard;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chatmessenger.DbHelper;

public class ContactProvider extends ContentProvider {

    DbHelper dbHelper;
    private static final int ALL_CONTACTS = 1;
    private static final int SINGLE_CONTACTS = 2;
    private static final String AUTHORITY = "com.example.chatmessenger.contentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contacts");
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contacts", ALL_CONTACTS);
        uriMatcher.addURI(AUTHORITY, "contacts/#", SINGLE_CONTACTS);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ContactDb.TABLE_NAME);
        String id = null;
        switch (uriMatcher.match(uri)){
            case ALL_CONTACTS:
                //Nothing
                break;

            case SINGLE_CONTACTS:
                id = uri.getLastPathSegment();
                queryBuilder.appendWhere(ContactDb.ROW_ID + "=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported Uri: " +uri);
        }

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case ALL_CONTACTS:
                return "vnd.android.cursor.dir/vnd.com.example.android.chatmessenger.contentprovider.contacts";
            case SINGLE_CONTACTS:
                return "vnd.android.cursor.item/vnd.com.example.android.chatmessenger.contentprovider.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI: " +uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case ALL_CONTACTS:
                //nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " +uri);
        }
        long id = db.insert(ContactDb.TABLE_NAME, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CONTENT_URI + "/" +id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case ALL_CONTACTS:
                //nothing
                break;
            case SINGLE_CONTACTS:
                String id = uri.getPathSegments().get(1);
                selection = ContactDb.ROW_ID + "=" +id +
                        (!TextUtils.isEmpty(selection) ?
                                "AND ("+selection+')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        int deleteCount = db.delete(ContactDb.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)){
            case ALL_CONTACTS:
                //do nothing
                break;
            case SINGLE_CONTACTS:
                String id = uri.getPathSegments().get(1);
                selection = ContactDb.ROW_ID + "=" +id +
                        (!TextUtils.isEmpty(selection) ?
                                "AND ("+selection+')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " +uri);
        }
        int updateCount = db.update(ContactDb.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return updateCount;
    }
}
