package com.example.SIT305_7_1P;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    //this is the class used to connect the program to the database

    //this is the constructor
    public Database(@Nullable Context context) {
        super(context, "Alerts_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //this override creates the alerts table in the database through executing the string in SQL
        String CREATE_ALERTS_TABLE = "CREATE TABLE ALERTS(ALERTID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, NAME TEXT, PHONE INTEGER, DESCRIPTION TEXT, DATE TEXT, LOCATION TEXT)";
        sqLiteDatabase.execSQL(CREATE_ALERTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //this override deletes and recreates the alerts table in the database
        String DROP_ALERTS_TABLE = "DROP TABLE IF EXISTS ALERTS";
        sqLiteDatabase.execSQL(DROP_ALERTS_TABLE);
        //the oncreate method is called to create the table in the database
        onCreate(sqLiteDatabase);
    }

    //this takes the alert passed into the element and puts the data of that alert into the database table
    public long InsertAlert(Alert alert) {
        //connect to the database and make it readable and writable
        SQLiteDatabase db = this.getWritableDatabase();
        //the values to insert into the table
        ContentValues Values = new ContentValues();
        Values.put("TYPE",alert.getLostOrFound());
        Values.put("NAME", alert.getName());
        Values.put("DESCRIPTION", alert.getDescription());
        Values.put("PHONE", alert.getPhone());
        Values.put("DATE", alert.getDate());
        Values.put("LOCATION", alert.getLocation());
        //insert the data into the table
        long row = db.insert("ALERTS",null, Values);
        //close the connection
        db.close();
        return row;
    }

    //this takes the alert passed into the element and deletes the matching alert in the database table
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
        String SELECT_ALL_ALERTS = "SELECT * FROM ALERTS";
        //cursor to select an individual entry
        Cursor cursor = db.rawQuery(SELECT_ALL_ALERTS, null);
        //looping through the entries
        if (cursor.moveToFirst()){
            do {
                //copy the data from the entry into a temp alert
                Alert temp = new Alert(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                //add the temp alert into the arraylist
                AlertList.add(temp);
            }while(cursor.moveToNext());
        }
        //return arraylist
        return AlertList;
    }
}
