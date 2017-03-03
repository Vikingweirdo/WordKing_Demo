package com.example.asus.workking.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asus on 2017/2/12.
 */

public class DatabaseOperate {

    private Context context = null;
    private String mTableName = null;
    private StuDBHelper mDB = null;
    private SQLiteDatabase mSQDB_read = null;        //数据库操作对象
    private SQLiteDatabase mSQDB_write = null;        //数据库操作对象

    public DatabaseOperate(Context context , String mTableName ){
        this.context = context;
        this.mTableName = mTableName;
        this.mDB = new StuDBHelper(context,mTableName,null,1);//创建表
    }

    public SQLiteDatabase getmSQDB_read(){
        this.mSQDB_read = mDB.getReadableDatabase(); //获取一个可读的数据库
        return mSQDB_read;
    }

    public SQLiteDatabase getmSQDB_write(){
        this.mSQDB_write = mDB.getWritableDatabase(); //获取一个可读的数据库
        return mSQDB_write;
    }

    public void OnCreateTables(SQLiteDatabase db){
        mDB.onCreate(db);
    }
}
