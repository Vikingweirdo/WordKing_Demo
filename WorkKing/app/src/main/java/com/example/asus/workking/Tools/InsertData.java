package com.example.asus.workking.Tools;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.workking.R;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by asus on 2017/3/4.
 */

public class InsertData {

    private SQLiteDatabase mSqLiteDatabase = null;
    private String mTableName = null;
    private ContentValues cv = null;

    public void insertData() {
        try{
            Field field = Class.forName("com.example.asus.workking.R$raw").getField("pledge");
            System.out.println("反射获取" + field.getInt(field));

        }catch (Exception e){}

       /* cv.put("id",5);
        cv.put("word","pledge");
        cv.put("imagepath", R.drawable.pledge);
        cv.put("mediapath",R.raw.pledge);
        cv.put("wordmean","v.新建");
        mSqLiteDatabase.insert(mTableName,null,cv);*/
    }

    public void setmSqLiteDatabase(SQLiteDatabase mSqLiteDatabase){
        this.mSqLiteDatabase = mSqLiteDatabase ;
    }

    public void setmTableName(String mTableName){
        this.mTableName = mTableName;
    }
}
