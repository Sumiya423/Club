package com.example.cpclubltd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cpc.bd";
    private static final String TABLE_NAME_ONGOING_EVENT = "table_ongoing_event";


    private static final String COLUMN_ONGOING_EVENT_ID = "event_id";
    private static final String COLUMN_ONGOING_EVENT_NAME = "event_name";
    private static final String COLUMN_ONGOING_EVENT_DATE = "date";
    private static final String COLUMN_ONGOING_EVENT_WING = "wing";
    private static final String COLUMN_ONGOING_EVENT_DETAILS = "details";

    public static final String displayOngoingTable = " SELECT * FROM "+ TABLE_NAME_ONGOING_EVENT;


    private static final int VERSION =1;
    private static final String QUERY_CREATE_ONGOING_EVENT_TABLE = "CREATE TABLE "
            + TABLE_NAME_ONGOING_EVENT
            + "(" + COLUMN_ONGOING_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ONGOING_EVENT_NAME + " TEXT, "
            + COLUMN_ONGOING_EVENT_WING + " TEXT, "
            + COLUMN_ONGOING_EVENT_DATE+ " TEXT, "
            + COLUMN_ONGOING_EVENT_DETAILS + " TEXT "
            + ");";



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE_ONGOING_EVENT_TABLE);

    }

    public Cursor show(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(displayOngoingTable,null);

        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ONGOING_EVENT);
        onCreate(sqLiteDatabase);
    }

    public long insertOngoingInfo(OngoingInfo ongoingInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ONGOING_EVENT_NAME, ongoingInfo.getName());
        contentValues.put(COLUMN_ONGOING_EVENT_WING, ongoingInfo.getWing());
        contentValues.put(COLUMN_ONGOING_EVENT_DATE, ongoingInfo.getDate());
        contentValues.put(COLUMN_ONGOING_EVENT_DETAILS, ongoingInfo.getDetails());


        long value = db.insert(TABLE_NAME_ONGOING_EVENT, null, contentValues);

        db.close();

        return value;
    }


    public List<OngoingInfo> getOngoingList() {
        List<OngoingInfo> ongoingList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ONGOING_EVENT + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                OngoingInfo ongoingModel = new OngoingInfo();
                ongoingModel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ONGOING_EVENT_ID)));
                ongoingModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_ONGOING_EVENT_NAME)));
                ongoingModel.setWing(cursor.getString(cursor.getColumnIndex(COLUMN_ONGOING_EVENT_WING)));
                ongoingModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_ONGOING_EVENT_DATE)));
                ongoingModel.setDetails(cursor.getString(cursor.getColumnIndex(COLUMN_ONGOING_EVENT_DETAILS)));

                ongoingList.add(ongoingModel);
            } while (cursor.moveToNext());
        }

        db.close();

        return ongoingList;
    }



}
