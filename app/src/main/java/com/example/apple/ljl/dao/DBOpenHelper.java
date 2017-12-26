package com.example.apple.ljl.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 2017/12/26.
 */
public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DBNAME = "account.db";

    public DBOpenHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_outaccount(id integer primary key,money decimal,time varchar(10)," +
                "type varchar(10),address varchar(100),mark varchar(200))");
        db.execSQL("create table tb_inaccount(id integer primary key,money decimal,time varchar(10)," +
                "type varchar(10),handler varchar(100),mark varchar(200))");
        db.execSQL("create table tb_pwd(password varchar(20))");
        db.execSQL("create table tb_flag(id integer primary key,flag varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
