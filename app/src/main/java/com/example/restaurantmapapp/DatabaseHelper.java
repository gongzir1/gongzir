package com.example.restaurantmapapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MAP_DATA.db";

    public static final String TABLE_NAME = "MAP_DATA_TABLE";

    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "LAT";
    public static final String COLUMN_4 = "LNG";
    public static final String COLUMN_5 = "TYPE";
    public static final String COLUMN_6 = "DESCRIPTION";
    public static final String COLUMN_7 = "PHONE";
    public static final String COLUMN_8 = "DATE";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LAT REAL, LNG REAL,TYPE TEXT,  DESCRIPTION TEXT,PHONE INTEGER, DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
       ;
    }
    public long InsertAlert(Alert alert) {
        //connect to the database and make it readable and writable
        SQLiteDatabase db = this.getWritableDatabase();
        //the values to insert into the table
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_5, alert.getLostOrFound());
        contentValues.put(COLUMN_6, alert.getDescription());
        contentValues.put(COLUMN_7, alert.getPhone());
        contentValues.put(COLUMN_8, alert.getDate());
        long row = db.insert(TABLE_NAME, null, contentValues);
        //insert the data into the table
        //close the connection
        db.close();
        return row;
    }
    public void DeleteAlert(Alert alert){
        //connect to the database and make it readable and writable
        SQLiteDatabase db = this.getWritableDatabase();
        //determine what alert to look for
        String WhereClause = "ALERTID=?";
        String[] WhereArgs = {String.valueOf(alert.getAlertID())};
        //delete the actual alert
        db.delete("ALERTS", WhereClause, WhereArgs);
        //close the connection
        db.close();
    }

    //this method returns all the alerts in the database table
    public ArrayList<Alert> fetchAllAlerts(){
        //create an arraylist
        ArrayList<Alert> AlertList = new ArrayList<>();
        //connect to the database and make it readable
        SQLiteDatabase db = this.getReadableDatabase();
        //query to return all the data from the table
        String SELECT_ALL_ALERTS = "SELECT * FROM "+TABLE_NAME;
        //cursor to select an individual entry
        Cursor cursor = db.rawQuery(SELECT_ALL_ALERTS, null);

        //looping through the entries
//        if (cursor.moveToFirst()){
//            do {
//                //copy the data from the entry into a temp alert
//                Alert temp = new Alert(cursor.getInt(0), cursor.getString(2), cursor.getString(5),
//                        cursor.getInt(7), cursor.getString(6),cursor.getString(8));
//                //add the temp alert into the arraylist
//                AlertList.add(temp);
//            }while(cursor.moveToNext());
//        }
//        //return arraylist
        return AlertList;
    }
    public long insertLocation(String name, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, name);
        contentValues.put(COLUMN_3, latitude);
        contentValues.put(COLUMN_4, longitude);
        long rowId = db.insert(TABLE_NAME, null, contentValues);
        return rowId;
    }

    public Cursor fetchAllMapData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
