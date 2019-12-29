package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MovieDB";
    public static final String TABLE_USER ="USER";
    public static final String COLU_1="USER_NAME";
    public static final String COLU_2="PASSWORD";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_USER+" ("+COLU_1+" TEXT PRIMARY KEY, "+COLU_2+" TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }
    public boolean is_exist_user(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from "+ TABLE_USER+" where "+COLU_1+" = \""+name+"\"",null);
        System.out.println(res.getCount());
        if(res.getCount()==0) //a keresett nev nem letezik a tablaban
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public boolean InsertData_User(String name,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLU_1,name);
        contentValues.put(COLU_2,password);
        long result = db.insert(TABLE_USER,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }
    public boolean login_check(String name,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from "+ TABLE_USER+" where "+COLU_1+" = \""+name+"\" and "+COLU_2+" = \""+password+"\"",null);
        System.out.println(res.getCount());
        if(res.getCount()==0) //a keresett nev nem letezik a tablaban
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
