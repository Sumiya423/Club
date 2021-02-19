package com.example.cpclubltd;

import android.app.Application;
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
    private static final String TABLE_NAME_UPCOMING_EVENT = "table_upcoming_event";
    private static final String TABLE_NAME_APPLICATION = "table_application";


    private static final String COLUMN_ONGOING_EVENT_ID = "event_id";
    private static final String COLUMN_ONGOING_EVENT_NAME = "event_name";
    private static final String COLUMN_ONGOING_EVENT_DATE = "date";
    private static final String COLUMN_ONGOING_EVENT_WING = "wing";
    private static final String COLUMN_ONGOING_EVENT_DETAILS = "details";

    private static final String COLUMN_UPCOMING_EVENT_ID = "up_event_id";
    private static final String COLUMN_UPCOMING_EVENT_NAME = "up_event_name";
    private static final String COLUMN_UPCOMING_EVENT_DATE = "up_last_date";
    private static final String COLUMN_UPCOMING_EVENT_WING = "up_wing";
    private static final String COLUMN_UPCOMING_EVENT_DETAILS = "up_details";

    private static final String COLUMN_STUDENT_ID = "stu_id";
    private static final String COLUMN_STUDENT_NAME = "stu_name";
    private static final String COLUMN_EVENT_ID = "eve_id";
    private static final String COLUMN_STUDENT_EMAIL = "stu_email";
    private static final String COLUMN_STUDENT_DETAILS = "stu_details";

    public static final String displayOngoingTable = " SELECT * FROM "+ TABLE_NAME_ONGOING_EVENT;

    public static final String displayUpcomingTable = " SELECT * FROM "+ TABLE_NAME_UPCOMING_EVENT;

    public static final String displayApplicationTable = " SELECT * FROM "+ TABLE_NAME_APPLICATION;


    private static final int VERSION =4;
    private static final String QUERY_CREATE_ONGOING_EVENT_TABLE = "CREATE TABLE "
            + TABLE_NAME_ONGOING_EVENT
            + "(" + COLUMN_ONGOING_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ONGOING_EVENT_NAME + " TEXT, "
            + COLUMN_ONGOING_EVENT_WING + " TEXT, "
            + COLUMN_ONGOING_EVENT_DATE+ " TEXT, "
            + COLUMN_ONGOING_EVENT_DETAILS + " TEXT "
            + ");";

    private static final String QUERY_CREATE_APPLICATION_TABLE = "CREATE TABLE "
            + TABLE_NAME_APPLICATION
            + "(" + COLUMN_STUDENT_ID+ " TEXT, "
            + COLUMN_STUDENT_NAME + " TEXT, "
            + COLUMN_EVENT_ID + " TEXT, "
            + COLUMN_STUDENT_EMAIL+ " TEXT, "
            + COLUMN_STUDENT_DETAILS+ " TEXT "
            + ");";

    private static final String QUERY_CREATE_UPCOMING_EVENT_TABLE = "CREATE TABLE "
            + TABLE_NAME_UPCOMING_EVENT
            + "(" + COLUMN_UPCOMING_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_UPCOMING_EVENT_NAME + " TEXT, "
            + COLUMN_UPCOMING_EVENT_WING + " TEXT, "
            + COLUMN_UPCOMING_EVENT_DATE+ " TEXT, "
            + COLUMN_UPCOMING_EVENT_DETAILS + " TEXT "
            + ");";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE_ONGOING_EVENT_TABLE);
        sqLiteDatabase.execSQL(QUERY_CREATE_UPCOMING_EVENT_TABLE);
        sqLiteDatabase.execSQL(QUERY_CREATE_APPLICATION_TABLE);

    }

    public Cursor show(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(displayOngoingTable,null);

        return cursor;
    }

    public Cursor showUp(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(displayUpcomingTable,null);

        return cursor;
    }

    public Cursor showApp(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(displayApplicationTable,null);

        return cursor;
    }

    public int deleteOngoing(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME_ONGOING_EVENT,COLUMN_ONGOING_EVENT_ID+"=?",new String[]{id});
        return value;
    }
    public int deleteUpcoming(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME_UPCOMING_EVENT,COLUMN_UPCOMING_EVENT_ID+"=?",new String[]{id});
        return value;
    }
    public int deleteApplication(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME_APPLICATION,COLUMN_STUDENT_ID+"=?",new String[]{id});
        return value;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ONGOING_EVENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UPCOMING_EVENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_APPLICATION);
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


    public long insertUpcomingInfo(UpcomingInfo upcomingInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_UPCOMING_EVENT_NAME, upcomingInfo.getUp_name());
        contentValues.put(COLUMN_UPCOMING_EVENT_WING, upcomingInfo.getUp_wing());
        contentValues.put(COLUMN_UPCOMING_EVENT_DATE, upcomingInfo.getUp_date());
        contentValues.put(COLUMN_UPCOMING_EVENT_DETAILS, upcomingInfo.getUp_details());


        long value = db.insert(TABLE_NAME_UPCOMING_EVENT, null, contentValues);

        db.close();

        return value;
    }

    public List<UpcomingInfo> getUpcomingList() {
        List<UpcomingInfo> upcomingList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_UPCOMING_EVENT + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                UpcomingInfo upcomingModel = new UpcomingInfo();
                upcomingModel.setUp_id(cursor.getString(cursor.getColumnIndex(COLUMN_UPCOMING_EVENT_ID)));
                upcomingModel.setUp_name(cursor.getString(cursor.getColumnIndex(COLUMN_UPCOMING_EVENT_NAME)));
                upcomingModel.setUp_wing(cursor.getString(cursor.getColumnIndex(COLUMN_UPCOMING_EVENT_WING)));
                upcomingModel.setUp_date(cursor.getString(cursor.getColumnIndex(COLUMN_UPCOMING_EVENT_DATE)));
                upcomingModel.setUp_details(cursor.getString(cursor.getColumnIndex(COLUMN_UPCOMING_EVENT_DETAILS)));

                upcomingList.add(upcomingModel);
            } while (cursor.moveToNext());
        }

        db.close();

        return upcomingList;
    }

    public long insertApplication(ApplInfo applicationInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_ID, applicationInfo.getsId());
        contentValues.put(COLUMN_STUDENT_NAME, applicationInfo.getsName());
        contentValues.put(COLUMN_EVENT_ID, applicationInfo.getEvID());
        contentValues.put(COLUMN_STUDENT_EMAIL, applicationInfo.getsEmail());
        contentValues.put(COLUMN_STUDENT_DETAILS, applicationInfo.getsDetails());


        long value = db.insert(TABLE_NAME_APPLICATION, null, contentValues);

        db.close();

        return value;
    }

    public List<ApplInfo> getApplicationList() {
        List<ApplInfo> appList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_APPLICATION + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ApplInfo applicationModel = new ApplInfo();
                applicationModel.setsId(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
                applicationModel.setsName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
                applicationModel.setEvID(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_ID)));
                applicationModel.setsEmail(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DETAILS)));
                applicationModel.setsDetails(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_DETAILS)));

                appList.add(applicationModel);
            } while (cursor.moveToNext());
        }

        db.close();

        return appList;
    }


}
