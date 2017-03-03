package com.example.asus.workking.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asus on 2017/2/12.
 */

public class InitTables {

    private Context mContext = null;
    String session = "A";
    private SQLiteDatabase db = null;
    private DatabaseOperate dbOpereate = null;

    public InitTables(Context context){
        this.mContext = context;
    }

    public void onCreateTables(){

        for (int i = 1 ; i <= 2 ; i++){

            for (int j = 1; j <= 2 ;j++){

                for (int k = 1 ; k<=8 ; k++){
                    String tablename = "book";
                    tablename+=i;
                    tablename+="_";
                    tablename+=k;
                    tablename+=session;
                    dbOpereate =  new DatabaseOperate(mContext,tablename);
                    //db = dbOpereate.getmSQDB_write();
                    //dbOpereate.OnCreateTables(db);
                    System.out.println(tablename);
                }
                session = "B";
            }
            session = "A";
        }

    }
}
