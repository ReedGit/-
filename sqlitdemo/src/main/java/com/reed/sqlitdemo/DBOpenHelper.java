package com.reed.sqlitdemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 伟 on 2015/12/24.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableName = "usertb";
        String sql = "create table if not exists " + tableName + " (_id integer primary key autoincrement, name text not null, sex text not null, age integer not null)";
        db.execSQL(sql);
        sql = "insert into " + tableName + "(name,sex,age) values('张三','男',18)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
