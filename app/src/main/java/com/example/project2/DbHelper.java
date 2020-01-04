package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MovieDB";
    public static final String TABLE_USER ="USER";
    public static final String COLU_1="USER_NAME";
    public static final String COLU_2="PASSWORD";
    public static  final String TABLE_FAVORITE="FAVORITE";
    public static  final String COLUMN_MOVIEID="MOVIEID";
    public static  final String COLUMN_USER="USER";
    public static  final String COLUMN_TITLE = "TITLE";
    public static  final String COLUMN_USERRATING="USERRATING";
    public static  final String COLUMN_POSTER_PATH="POSTER_PATH";
    public static  final String COLUMN_OVERVIEW="OVERVIEW";
    public static  final String COLUMN_RELEASE_DATE="RELEASE_DATE";
    public static  final String _ID="ID";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_USER+" ("+COLU_1+" TEXT PRIMARY KEY, "+COLU_2+" TEXT);");
        db.execSQL("CREATE TABLE "+ TABLE_FAVORITE+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_MOVIEID+" INTEGER, "+COLUMN_USER+" TEXT,"+COLUMN_TITLE+" TEXT, "+
                COLUMN_USERRATING+" TEXT, "+COLUMN_POSTER_PATH+" TEXT, "+COLUMN_OVERVIEW+ " TEXT, "+COLUMN_RELEASE_DATE+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FAVORITE);
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
    public void addFavorite(Movie movie,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIEID,movie.getId());
        values.put(COLUMN_USER,username);
        values.put(COLUMN_TITLE,movie.getOriginalTitle());
        values.put(COLUMN_USERRATING,movie.getVoteAverage());
        values.put(COLUMN_POSTER_PATH,movie.getPosterPath());
        values.put(COLUMN_OVERVIEW,movie.getOverview());
        values.put(COLUMN_RELEASE_DATE,movie.getReleaseDate());
        db.insert(TABLE_FAVORITE,null,values);
        db.close();
    }
    public void deleteFavourite(int id,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_FAVORITE+" where "+COLUMN_MOVIEID+" = "+id+" and "+ COLUMN_USER+" ='"+username+"';");
    }
    public List<Movie> getAllFavorite(String name){
        String [] columns={
                _ID,
                COLUMN_MOVIEID,
                COLUMN_USER,
                COLUMN_TITLE,
                COLUMN_USERRATING,
                COLUMN_POSTER_PATH,
                COLUMN_OVERVIEW,
                COLUMN_RELEASE_DATE
        };
        String sortOrder =_ID +" ASC";
        List<Movie> favoriteList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITE,columns,null,null,null,null,sortOrder);
        if(cursor.moveToFirst()){
            do{
                String user=cursor.getString(cursor.getColumnIndex(COLUMN_USER));
                if(user.equals(name)) {
                    Movie movie = new Movie();
                    movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_MOVIEID))));
                    movie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                    movie.setVoteAverage(cursor.getString(cursor.getColumnIndex(COLUMN_USERRATING)));
                    movie.setPosterPath(cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH)));
                    movie.setOverview(cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW)));
                    movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE)));
                    favoriteList.add(movie);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteList;
    }
}
