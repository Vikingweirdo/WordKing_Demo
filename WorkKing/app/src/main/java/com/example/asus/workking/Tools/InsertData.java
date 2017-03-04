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

    public InsertData() {
        this.cv = new ContentValues();
    }

    public void insertData() {
        Class image = null;
        Class media = null;
        try {    //利用反射获取资源文件内容
            image = Class.forName("com.example.asus.workking.R$drawable");
            media = Class.forName("com.example.asus.workking.R$raw");
            //Field field = Class.forName("com.example.asus.workking.R$raw").getField("pledge");
            //System.out.println("反射获取" + field.getInt(field));

            //循环插入数据库
            String[] book1_1A_words = Words.getBook1_1A_words();
            String[] book1_1A_means = Words.getBook1_1A_means();
            for (int x = 0; x < book1_1A_words.length; x++) {
                cv.put("id", x);
                cv.put("word", book1_1A_words[x]);

                Field path = image.getField(book1_1A_words[x]);
                cv.put("imagepath", path.getInt(path));//获取资源地址

                path = media.getField(book1_1A_words[x]);
                cv.put("mediapath",path.getInt(path));
                cv.put("wordmean", book1_1A_means[x]);
                mSqLiteDatabase.insert(mTableName, null, cv);
            }


        } catch (Exception e) {
        }

    }

    public void setmSqLiteDatabase(SQLiteDatabase mSqLiteDatabase) {
        this.mSqLiteDatabase = mSqLiteDatabase;
    }

    public void setmTableName(String mTableName) {
        this.mTableName = mTableName;
    }


}
