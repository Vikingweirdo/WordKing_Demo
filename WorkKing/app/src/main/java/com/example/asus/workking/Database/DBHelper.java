package com.example.asus.workking.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by asus on 2017/2/12.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "Workking";
    public static final int VERSION = 1;

    private String mTableName = null;

    private SQLiteDatabase db = null;

    //必须要有构造函数
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, "MyData", factory, version);
        this.mTableName = name;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        String sql = "create table " + mTableName + "(id integer primary key autoincrement,word varchar(20),imagepath int,mediapath int,wordmean varchar(10))";
        //输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->");
        //execSQL函数用于执行SQL语句
        this.db = sqLiteDatabase;
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createTable(SQLiteDatabase methodDB, String methodTableName) {

        //创建表
        String sql = "create table " + methodTableName + "(id integer primary key autoincrement,word varchar(20),imagepath int,mediapath int,wordmean varchar(10))";
        //输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->my method");
        //执行SQL语句
        methodDB.execSQL(sql);
    }



}
