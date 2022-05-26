package com.example.trucksharingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.ULocale;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.trucksharingapp.Order;
import com.example.trucksharingapp.model.User;
import com.example.trucksharingapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.USERNAME + " TEXT," +Util.PASSWORD +" TEXT,"+Util.IMAGE+ " TEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        String CREATE_USER_TABLE_TWO= "CREATE TABLE " + Util.TABLE_NAME2 + "(" +Util.ORDER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT , "
                +Util.DATE+ " TEXT,"+Util.TIME+ " TEXT,"+Util.LOCATION+ " TEXT,"+Util.GOODTYPE+ " TEXT,"+Util.WEIGHT+ " TEXT,"+Util.HEIGHT
                + " TEXT,"+Util.LENGTH+ " TEXT,"+Util.WIDTH+ " TEXT,"+Util.VEHICLE+ " TEXT,"+Util.USERNAME+" TEXT,"
                + " FOREIGN KEY ("+Util.USERNAME+") REFERENCES "+Util.TABLE_NAME+"("+Util.USERNAME+"));";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE_TWO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        String DROP_USER_TABLE="DROP TABLE IF EXISTS";
//        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME2);
        onCreate(sqLiteDatabase);
        Log.d("upgradeabc", "1 ");
    }
    public long insertUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.USERNAME,user.getUser_name());
        contentValues.put(Util.PASSWORD,user.getPassword());
        contentValues.put(Util.IMAGE,user.getImage());
        long newRowId=db.insert(Util.TABLE_NAME,null,contentValues);

        Log.d("insertabc", "1 ");
        return newRowId;
    }
    public boolean fetchUser(String username,String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.USER_ID},Util.USERNAME+"=? and "+Util.PASSWORD+"=?",new String[]{username,password},null,null,null);       int numberOfRows=cursor.getCount();
        db.close();

        if(numberOfRows>0)
            return true;
        else
            return false;
    }
    public boolean fetchUser(String username){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM " + Util.TABLE_NAME,null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(username)) {
                    return false;
                }
            }
            while (cursor.moveToNext());
        }
        return true;
    }
    public long insertOrder(Order order){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.DATE,order.getDate());
        contentValues.put(Util.TIME,order.getTime());
        contentValues.put(Util.LOCATION,order.getLocation());
        contentValues.put(Util.GOODTYPE,order.getGoodtype());
        contentValues.put(Util.WEIGHT,order.getWeight());
        contentValues.put(Util.HEIGHT,order.getHeight());
        contentValues.put(Util.LENGTH,order.getLength());
        contentValues.put(Util.WIDTH,order.getWidth());
        contentValues.put(Util.VEHICLE,order.getVehicle());
        contentValues.put(Util.USERNAME,order.getUsername());
        long newRowID=db.insert(Util.TABLE_NAME2,null,contentValues);
//        db.close();
        return newRowID;
    }
    public List<Order> fetchAllOrder(String username){
        List<Order>orderList=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + Util.TABLE_NAME2,null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(10).equals(username)){
                    Order order=new Order();
                    order.setId(cursor.getInt(0));
                    order.setDate(cursor.getString(1));
                    order.setTime(cursor.getString(2));
                    order.setLocation(cursor.getString(3));
                    order.setGoodtype(cursor.getString(4));
                    order.setWeight(cursor.getString(5));
                    order.setHeight(cursor.getString(6));
                    order.setLength(cursor.getString(7));
                    order.setWidth(cursor.getString(8));
                  //  order.setVehicle(cursor.getString(9));

                    orderList.add(order);
                }
            }
            while (cursor.moveToNext());
        }
        db.close();
        return orderList;
    }

}
