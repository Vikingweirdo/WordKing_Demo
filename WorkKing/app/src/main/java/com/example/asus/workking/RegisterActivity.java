package com.example.asus.workking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.example.asus.workking.BmobBean.User;
import com.example.asus.workking.Tools.RegsterThread;

public class RegisterActivity extends AppCompatActivity {

    public static SQLiteDatabase mOperate = null;   //操作数据库
    public final static String FILENAME = "loadflag";
    public final static String TABLENAME = "UserInfo";


    //初始化组件
    private EditText mUsername = null;
    private EditText mPassword = null;
    private EditText mReconfim = null;
    private ImageButton mEnter = null;
    private ProgressDialog progressDialog = null;

    private SharedPreferences mSharePreferences = null;
    private SharedPreferences.Editor mEditor = null;

    private Handler handle = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 0x123:
                    Toast.makeText(RegisterActivity.this, "Regster success", Toast.LENGTH_SHORT).show();
                    mEditor.putInt("loadFlag",0);
                    mEditor.commit();
                    Intent inten = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(inten);
                    overridePendingTransition(R.anim.back_in_anim, R.anim.back_out_anim);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.register);
        Bmob.initialize(this,"bccfd1c2f14ea95098df53a7dcf20ca9");
        //实例化组件
        initView();

        //register listening
        this.mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Username or password is empty",Toast.LENGTH_SHORT).show();
                    return;
                }else if (isEquals()){

                    /*new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = 0x123;
                            //Inser();
                            regBmobUser();//Bmob
                            try {
                                Thread.sleep(1000);
                                handle.sendMessage(msg);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }).start();
                    */

                    progressDialog.setTitle("Waitting");
                    progressDialog.setMessage("Loading");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    regBmobUser();


                }else{
                    Toast.makeText(RegisterActivity.this,"Keep password equals",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {

        this.mUsername = (EditText) super.findViewById(R.id.Rusername);
        this.mPassword = (EditText) super.findViewById(R.id.Rpassword);
        this.mReconfim = (EditText) super.findViewById(R.id.Rreconfim);
        this.mEnter = (ImageButton)super.findViewById(R.id.registerbtu);

        this.mSharePreferences = super.getSharedPreferences(FILENAME,
                Activity.MODE_PRIVATE);
        this.mEditor = mSharePreferences.edit();

        MainActivity.mSharePreferences = this.mSharePreferences;    //取出数据使用

        this.progressDialog = new ProgressDialog(RegisterActivity.this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            Intent inten = new Intent(this, LoginActivity.class);
            startActivity(inten);
            overridePendingTransition(R.anim.back_in_anim, R.anim.back_out_anim);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //判断空
    public boolean isEmpty() {
        if ("".equals(this.mUsername.getText().toString()) || "".equals(this.mPassword.getText().toString()) || "".equals(this.mReconfim.getText().toString())){
            return true;
        }
        return false;
    }

    //判断是否密码一致
    public boolean isEquals(){
        if (mPassword.getText().toString().equals(mReconfim.getText().toString())){
            return true;
        }
        return false;
    }

    //Insert databases
    public void Inser(){
        ContentValues cv = new ContentValues();
        cv.put("word",mUsername.getText().toString());
        cv.put("imagepath",0);
        cv.put("mediapath",0);
        cv.put("wordmean",mPassword.getText().toString());
        mOperate.insert(TABLENAME,null,cv);
    }

    public void regBmobUser(){
        String name = mUsername.getText().toString();
        String pass = mPassword.getText().toString();
        User user = new User();
        user.setName(name);
        user.setPass(pass);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String id, BmobException e) {
                Log.i("Bmob-->ID" , id);
                if (e == null){
                    Message msg = new Message();
                    msg.what = 0x123;
                    handle.sendMessage(msg);
                    progressDialog.dismiss();
                    //Toast.makeText(RegisterActivity.this,"success" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"Check your net" , Toast.LENGTH_SHORT).show();
                    Log.i("Bmob-->Exception" , e.toString());
                }
            }
        });
    }
}
