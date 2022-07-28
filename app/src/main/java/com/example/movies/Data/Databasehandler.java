package com.example.movies.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.movies.model.Info;
import com.example.movies.Utils.Util;

public class Databasehandler extends SQLiteOpenHelper {

    public Databasehandler(Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER = "CREATE TABLE " + Util.tb1 + "("
                + Util.id + " INTEGER PRIMARY KEY,"
                + Util.name + " TEXT,"
                + Util.email + " TEXT,"
                + Util.password + " TEXT,"
                + Util.mobile + " NUMBER" + ")";

        String CHATS = "CREATE TABLE " + Util.tb4 + "("
                + Util.id + " INTEGER PRIMARY KEY,"
                + Util.sender + " TEXT,"
                + Util.receiver + " TEXT,"
                + Util.message + " NUMBER" + ")";

        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CHATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.tb1);
    }

    public Boolean searchStudent(String username,String password){
        SQLiteDatabase db=getReadableDatabase();
        System.out.println(username);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Util.tb1+" WHERE "+Util.name+" = ? AND "+Util.password+" =? ",
                new String[]{username,password});
        cursor.moveToFirst();
        int count = cursor.getCount();
        if(count>0){
            return true;
        }
        else
            return false;
    }

    public void addStudent(Info info){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.name,info.getName());
        value.put(Util.email,info.getEmail());
        value.put(Util.password,info.getPassword());
        value.put(Util.mobile,info.getMobile());
        Log.v("TAG", "Info"+value );
        db.insert(Util.tb1,null,value);
        db.close();
    }

    public Boolean userExists(String username){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Util.tb1+" WHERE "+Util.name+" = ? ",
                new String[]{username});
        cursor.moveToFirst();
        int count = cursor.getCount();
        if(count>0){
            return true;
        }
        else
            return false;
    }

    public Boolean updatePassword(String username, String p1)
    {
        System.out.println("In db"+username+p1);
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor= myDB.rawQuery("UPDATE "+Util.tb1+" SET "+Util.password+" = '"+p1+"' WHERE "+
                Util.name+" = ?",new String[]{username});
        cursor.moveToFirst();
        int count = cursor.getCount();
        if(count>0){
            return true;
        }
        else
            return false;

    }
}