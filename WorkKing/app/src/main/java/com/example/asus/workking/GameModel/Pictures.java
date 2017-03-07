package com.example.asus.workking.GameModel;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.MyDialog;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.Tools.Words;

import java.util.Random;

public class Pictures extends AppCompatActivity implements View.OnClickListener{
    private ImageButton answer1 = null;
    private ImageButton answer2 = null;
    private ImageButton answer3 = null;
    private ImageButton answer4 = null;
    private TextView word = null;

    private RandomModel mRandom = null;     //产生随机数
    private SharedPreferences mSharedPreferences = null;//游戏结束就设置进度
    private SharedPreferences.Editor mEditor = null;
    private int modleFlag;  //模式标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pictures);


        initView();
    }

    private void initView() {
        this.answer1 = (ImageButton)super.findViewById(R.id.answer1);
        this.answer2 = (ImageButton)super.findViewById(R.id.answer2);
        this.answer3 = (ImageButton)super.findViewById(R.id.answer3);
        this.answer4 = (ImageButton)super.findViewById(R.id.answer4);
        this.word = (TextView)super.findViewById(R.id.word);

        this.mRandom = new RandomModel();

        this.answer1.setOnClickListener(this);
        this.answer2.setOnClickListener(this);
        this.answer3.setOnClickListener(this);
        this.answer4.setOnClickListener(this);



        loadData(); //loading game data
        getModleFlag(); //获取测试的标志，判断是否是测试模式

    }

    private void getModleFlag() {
        Intent intent = super.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            this.modleFlag = bundle.getInt("modleFlag");
        }else {
            this.modleFlag = 0x000;
        }
    }

    private void loadData() {

        System.out.println("数据长度" + Record.mGamePross);
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.word.setText(MainActivity.mWord);
        this.answer2.setBackgroundResource(MainActivity.mPicturePath);


        //设置干扰项
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer1.setBackgroundResource(MainActivity.mPicturePath);

        //this.answer1.setBackgroundResource(MainActivity.mPicturePath);

        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer3.setBackgroundResource(MainActivity.mPicturePath);
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer4.setBackgroundResource(MainActivity.mPicturePath);

    }

    @Override
    public void onClick(View view) {
        if(Record.mGamePross!= Record.mWordCount) {

            int position;
            switch (view.getId()) {
                case R.id.answer1:
                    Record.mGamePross++;
                    if (modleFlag == 0x111){    //只在改模块下循环游戏
                        Intent mIntent = new Intent(Pictures.this, Pictures.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("modleFlag",0x111);   //判断是否是测试模式
                        mIntent.putExtras(bundle);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                        finish();
                    }else {
                        position = mRandom.getModelSum();
                        IntenActivity(position);//intent activity
                    }
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.answer2:
                    Record.mGamePross++;
                    Record.mRightCount++;
                    Toast.makeText(Pictures.this, "TRUE", Toast.LENGTH_SHORT).show();
                    if (modleFlag == 0x111){    //只在改模块下循环游戏
                        Intent mIntent = new Intent(Pictures.this, Pictures.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("modleFlag",0x111);   //判断是否是测试模式
                        mIntent.putExtras(bundle);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                        finish();
                    }else {
                        position = mRandom.getModelSum();
                        IntenActivity(position);//intent activity
                    }
                    break;

                case R.id.answer3:
                    Record.mGamePross++;
                    if (modleFlag == 0x111){    //只在改模块下循环游戏
                        Intent mIntent = new Intent(Pictures.this, Pictures.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("modleFlag",0x111);   //判断是否是测试模式
                        mIntent.putExtras(bundle);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                        finish();
                    }else {
                        position = mRandom.getModelSum();
                        IntenActivity(position);//intent activity
                    }
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.answer4:
                    Record.mGamePross++;
                    if (modleFlag == 0x111){    //只在改模块下循环游戏
                        Intent mIntent = new Intent(Pictures.this, Pictures.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("modleFlag",0x111);   //判断是否是测试模式
                        mIntent.putExtras(bundle);
                        startActivity(mIntent);
                        overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                        finish();
                    }else {
                        position = mRandom.getModelSum();
                        IntenActivity(position);//intent activity
                    }
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{      //游戏结束
            Toast.makeText(Pictures.this, "Game Over", Toast.LENGTH_SHORT).show();
            Toast.makeText(Pictures.this, "Right Count :" + Record.mRightCount +
                            "Wrong Count :" + (Record.mWordCount - Record.mRightCount)
                    , Toast.LENGTH_SHORT).show();

            //实例化
            if (modleFlag!=0x111) {
                this.mSharedPreferences = super.getSharedPreferences(MainActivity.GAMEPROSS,
                        Activity.MODE_PRIVATE);
                this.mEditor = mSharedPreferences.edit();
                mEditor.putString("record_count", String.valueOf(Record.mRightCount));
                mEditor.commit();
            }
            Intent intent = new Intent(Pictures.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
            finish();
            return;
        }
    }



    //Intent activity
    public void IntenActivity(int position){

        switch (position){

            case 0:
                Intent intent = new Intent(Pictures.this,Translation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(Pictures.this,Spelling.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(Pictures.this,Pictures.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(Pictures.this,Listening.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
        }
    }



    //先new出一个监听器，设置好监听
    private DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){
                case Dialog.BUTTON_POSITIVE:
                    Intent intent = new Intent(Pictures.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                    finish();
                    break;
                //case Dialog.BUTTON_NEGATIVE:

                //   break;
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    //返回键的监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            MyDialog myDialog = null;
            myDialog = new MyDialog(Pictures.this);
            myDialog.showDialog(dialogOnclicListener);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
