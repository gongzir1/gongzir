package com.example.learningsql.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.learningsql.model.User;
import com.example.learningsql.model.YoutubeLink;
import com.example.learningsql.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String CREATE_USER_TABLE= "CREATE TABLE "+Util.TABLE_NAME+"("+Util.USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+Util.USER_NAME+"TEXT"+Util.PASSWORD+"TEXT)";
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.USERNAME + " TEXT," +Util.PASSWORD + " TEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        String CREATE_USER_TALBE2= "CREATE TABLE " + Util.TABLE_NAME2 + "(" + Util.URL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                +Util.LINK+" TEXT,"+Util.USERNAME+" TEXT,"
                + " FOREIGN KEY ("+Util.USERNAME+") REFERENCES "+Util.TABLE_NAME+"("+Util.USERNAME+"));";
        sqLiteDatabase.execSQL(CREATE_USER_TALBE2);
        Log.d("createabc", "1 ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        String DROP_USER_TABLE="DROP TABLE IF EXISTS";
//        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.USERNAME,user.getUser_name());
        contentValues.put(Util.PASSWORD,user.getPassword());
        long newRowId=db.insert(Util.TABLE_NAME,null,contentValues);
        db.close();

        return newRowId;
    }
    public long insertLink(YoutubeLink link){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.LINK,link.getLink());
        contentValues.put(Util.USERNAME,link.getUser());
        long newRowId=db.insert(Util.TABLE_NAME2,null,contentValues);
        return newRowId;
    }
    public boolean fetchUser(String username,String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.query(Util.TABLE_NAME,new String[]{Util.USER_ID},Util.USERNAME+"=? and "+Util.PASSWORD+"=?",new String[]{username,password},null,null,null);
//        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.USER_ID,Util.USERNAME,Util.PASSWORD}, Util.USERNAME + "=? and " + Util.PASSWORD + "=?",
//                new String[] {username, password}, null, null, null);
        int numberOfRows=cursor.getCount();

        Log.d("fetchabc", "1 ");
        if(numberOfRows>0)
            return true;
        else
            return false;
    }
    public List<YoutubeLink>fetchAllLink(String username){
        List<YoutubeLink>linkList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT*FROM "+Util.TABLE_NAME2,null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(username)){
                    YoutubeLink link=new YoutubeLink();
                    link.setId(cursor.getInt(0));
                    link.setLink(cursor.getString(1));
                    linkList.add(link);

                }
            }
            while(cursor.moveToNext());
        }
        return linkList;
    }


}
