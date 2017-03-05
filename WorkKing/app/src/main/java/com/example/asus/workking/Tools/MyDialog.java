package com.example.asus.workking.Tools;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.asus.workking.GameModel.Listening;
import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;

/**
 * Created by asus on 2017/3/5.
 */

public class MyDialog {
    private Context mContext = null;

    public MyDialog(Context mContext){
        this.mContext = mContext;
    }

    public void showDialog(DialogInterface.OnClickListener dialogOnclicListener){
        //dialog参数设置
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);  //先得到构造器
        builder.setTitle("Notification"); //设置标题
        builder.setMessage("Do you want to exit?if you done,you will lose this progress"); //设置内容
        //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("Sure",dialogOnclicListener);
        builder.setNegativeButton("Cancle", dialogOnclicListener);
        //builder.setNeutralButton("忽略", dialogOnclicListener);
        builder.create().show();
    }
}
