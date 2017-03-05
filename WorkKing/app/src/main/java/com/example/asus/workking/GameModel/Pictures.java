package com.example.asus.workking.GameModel;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
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
    private int mRightPath ;

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

    }

    private void loadData() {

        System.out.println("数据长度" + Record.mGamePross);
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.word.setText(MainActivity.mWord);
        this.answer2.setBackgroundResource(MainActivity.mPicturePath);
        this.mRightPath = MainActivity.mPicturePath;

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
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.answer2:
                    Record.mGamePross++;
                    Record.mRightCount++;
                    Toast.makeText(Pictures.this, "TRUE", Toast.LENGTH_SHORT).show();
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    break;

                case R.id.answer3:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.answer4:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    Toast.makeText(Pictures.this, "WRONG", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{      //游戏结束
            Toast.makeText(Pictures.this, "Game Over", Toast.LENGTH_SHORT).show();
            Toast.makeText(Pictures.this, "Right Count :" + Record.mRightCount +
                            "Wrong Count :" + (Record.mWordCount - Record.mRightCount)
                    , Toast.LENGTH_SHORT).show();
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
}
