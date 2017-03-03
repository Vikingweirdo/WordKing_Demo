package com.example.asus.workking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.Database.DBHelper;


public class LoginActivity extends AppCompatActivity {

    //初始化界面，用户名输入，密码输入，按钮，注册
    private EditText mUsername = null;
    private EditText mPassword = null;
    private TextView mRegister = null;
    private ImageButton mLogin = null;
    private RadioButton mStudent = null;
    private RadioButton mTeacher = null;
    private ProgressBar mProBar = null;

    private int select;

    //Handle操作界面跳转
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //界面跳转
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                    finish();
                break;
            }
        }
    };



    //查询数据库对象
    private Cursor mCursor = null;
    private DBHelper mDataBases =null;//数据库对象
    private SQLiteDatabase mDataOperate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.login);


        //实例化组件
        initView();


        //设置注册文字监听
        this.mRegister = (TextView) super.findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //跳转至注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                finish();
            }
        });

        //设置按钮监听
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) {

                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mProBar.setVisibility(View.VISIBLE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            while (mCursor.moveToNext()) {

                                if (mCursor.getString(1).equals(mUsername.getText().toString()) &&
                                        mCursor.getString(4).equals(mPassword.getText().toString())) {

                                    MainActivity.myDB = mDataBases;
                                    //发送handle信号
                                    Message msg = new Message();
                                    msg.what = 1;
                                    handler.sendMessage(msg);

                                } else {
                                    mProBar.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Username or password is wrong", Toast.LENGTH_SHORT).show();
                                }

                            }

                        } catch (Exception e) {}

                    }
                }).start();


            }
        });

        mStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = 0;     //student
            }
        });
        mTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = 1;     //teacher
            }
        });
    }


    //判断空
    public boolean isEmpty() {
        if ("".equals(mUsername.getText().toString()) || "".equals(mPassword.getText().toString())) {
            return true;
        }
        return false;
    }


    //init view
    public void initView(){
        this.mLogin = (ImageButton) super.findViewById(R.id.login);
        this.mUsername = (EditText) super.findViewById(R.id.username);
        this.mPassword = (EditText) super.findViewById(R.id.password);
        this.mStudent = (RadioButton) super.findViewById(R.id.selectStudent);
        this.mTeacher = (RadioButton) super.findViewById(R.id.selectTeacher);
        this.mProBar = (ProgressBar) super.findViewById(R.id.progressBar);
        this.mDataBases = new DBHelper(LoginActivity.this,"UserInfo",null,1);
        this.mDataOperate = mDataBases.getReadableDatabase();
        this.mCursor = mDataOperate.query("UserInfo",null,null,null,null,null,null);

        RegisterActivity.mOperate = mDataBases.getWritableDatabase();
    }
}
