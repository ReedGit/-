package com.reed.sqlitdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //利用SQLiteOpenHelper操作数据库
        String dbName = "user.db";
        DBOpenHelper helper = new DBOpenHelper(MainActivity.this,dbName,null,1);
        //helper.getReadableDatabase();//获取只读数据库
        SQLiteDatabase db = helper.getWritableDatabase();

        /*//创建一个数据库并且打开
        String dbName = "user.db";
        String tableName = "usertb";
        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        String sql = "create table if not exists " + tableName + " (_id integer primary key autoincrement, name text not null, sex text not null, age integer not null)";
        db.execSQL(sql);

        //系统提供的方法
        ContentValues values = new ContentValues();
        values.put("name","天明");
        values.put("age",20);
        values.put("sex", "男");
        db.insert(tableName, null, values);
        values.clear();
        values.put("name", "高月");
        values.put("age", 17);
        values.put("sex", "女");
        db.insert(tableName, null, values);
        values.clear();
        values.put("name", "少羽");
        values.put("age", 20);
        values.put("sex", "女");
        db.insert(tableName, null, values);
        values.clear();
        values.put("name", "卫庄");
        values.put("age", 30);
        values.put("sex", "男");
        db.insert(tableName, null, values);
        values.clear();
        values.put("sex", "男");
        db.update(tableName, values, "name = ?", new String[]{"少羽"});
        values.clear();
        db.delete(tableName, "name = ?", new String[]{"卫庄"});
        Cursor cursor = db.query(tableName, null, null, null, null, null, "name");
        if (cursor != null) {
            String[] columns  = cursor.getColumnNames();
            while (cursor.moveToNext()) {
                for (String columnName : columns) {
                    Log.i("info",columnName+"："+cursor.getString(cursor.getColumnIndex(columnName)));
                }
            }
            cursor.close();
        }
        db.close();*/


        /*//sql语句的方式
        sql = "insert into " + tableName + "(name,sex,age) values('张三','男',18)";
        db.execSQL(sql);
        sql = "select * from " + tableName;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Log.i("info","_id:"+cursor.getInt(cursor.getColumnIndex("_id")));
                Log.i("info","name:"+cursor.getString(cursor.getColumnIndex("name")));
                Log.i("info","sex:"+cursor.getString(cursor.getColumnIndex("sex")));
                Log.i("info","age:"+cursor.getInt(cursor.getColumnIndex("age")));

            }
            cursor.close();//注意释放内存
        }
        db.close();//注意释放内存*/
    }
}
