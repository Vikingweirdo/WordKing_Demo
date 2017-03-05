package com.example.asus.workking.GameModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.Tools.Words;
import com.example.asus.workking.ViewPageFragment.HomeFragment;

import java.util.Random;

public class Translation extends AppCompatActivity implements View.OnClickListener {
    public static String Problem = null;
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static String D = null;

    private Button answer1 = null;
    private Button answer2 = null;
    private Button answer3 = null;
    private Button answer4 = null;
    private TextView wordmean = null;

    private RandomModel mRandom = null;
    private String mRight = null ;   //保存正确答案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_translation);

        initView();
    }

    private void initView() {
        this.answer1 = (Button)super.findViewById(R.id.answer1);
        this.answer2 = (Button)super.findViewById(R.id.answer2);
        this.answer3 = (Button)super.findViewById(R.id.answer3);
        this.answer4 = (Button)super.findViewById(R.id.answer4);
        this.wordmean = (TextView)super.findViewById(R.id.wordmean);

        this.mRandom = new RandomModel();

        this.answer1.setOnClickListener(this);
        this.answer2.setOnClickListener(this);
        this.answer3.setOnClickListener(this);
        this.answer4.setOnClickListener(this);


        loadData();//load game data

    }

    private void loadData() {

        System.out.println("数据长度" + Record.mGamePross);
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.wordmean.setText(MainActivity.mWordMean);
        this.answer1.setText(MainActivity.mWord);
        this.mRight = MainActivity.mWord;

       //设置干扰项
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer2.setText(MainActivity.mWord);
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer3.setText(MainActivity.mWord);
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.answer4.setText(MainActivity.mWord);
    }

    @Override
    public void onClick(View view) {

        if(Record.mGamePross!=Record.mWordCount) {

            int position;
            switch (view.getId()) {
                case R.id.answer1:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(answer1.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Translation.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Translation.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.answer2:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(answer2.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Translation.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Translation.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.answer3:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(answer3.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Translation.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Translation.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.answer4:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(answer4.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Translation.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Translation.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }else{      //游戏结束

            Toast.makeText(Translation.this, "Game Over", Toast.LENGTH_SHORT).show();
            Toast.makeText(Translation.this, "Right Count :" + Record.mRightCount +
                    "Wrong Count :" + (Record.mWordCount - Record.mRightCount)
                    , Toast.LENGTH_SHORT).show();
            HomeFragment.mShowCont.setText(String.valueOf(Record.mRightCount));//主界面显示游戏完成进度
            Intent intent = new Intent(Translation.this, MainActivity.class);
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
                Intent intent = new Intent(Translation.this,Translation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(Translation.this,Spelling.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(Translation.this,Pictures.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(Translation.this,Listening.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
        }
    }
}
