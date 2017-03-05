package com.example.asus.workking.GameModel;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asus.workking.LoginActivity;
import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.Tools.Words;
import com.example.asus.workking.ViewPageFragment.HomeFragment;

import java.util.Random;

public class Listening extends AppCompatActivity implements View.OnClickListener {

    //prepared the view
    private Button mAnswer1 = null;
    private Button mAnswer2 = null;
    private Button mAnswer3 = null;
    private Button mAnswer4 = null;
    private ImageButton mPlay = null;
    private MediaPlayer mMediaPlayer = null;

    private RandomModel mRandom = null;
    private int mMediaPath ;
    private String mRight = null ;   //保存正确答案

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_listening);

        initView();
    }


    //init view and loading data
    private void initView() {
        this.mAnswer1 = (Button)super.findViewById(R.id.answer1);
        this.mAnswer2 = (Button)super.findViewById(R.id.answer2);
        this.mAnswer3 = (Button)super.findViewById(R.id.answer3);
        this.mAnswer4 = (Button)super.findViewById(R.id.answer4);
        this.mPlay = (ImageButton)super.findViewById(R.id.play);


        this.mRandom = new RandomModel();

        this.mAnswer1.setOnClickListener(this);
        this.mAnswer2.setOnClickListener(this);
        this.mAnswer3.setOnClickListener(this);
        this.mAnswer4.setOnClickListener(this);
        this.mPlay.setOnClickListener(this);


        loadData();
    }

    private void loadData() {

        System.out.println("数据长度" + Record.mGamePross);
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.mAnswer3.setText(MainActivity.mWord);
        this.mMediaPath = MainActivity.mMediaPath;
        this.mRight = MainActivity.mWord;

        //设置干扰项
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.mAnswer2.setText(MainActivity.mWord);
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.mAnswer1.setText(MainActivity.mWord);
        MainActivity.getPositionData(mRandom.getWordPosition());
        this.mAnswer4.setText(MainActivity.mWord);
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
                    if(mAnswer1.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Listening.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Listening.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.answer2:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(mAnswer2.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Listening.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Listening.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.answer3:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(mAnswer3.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Listening.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Listening.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.answer4:
                    Record.mGamePross++;
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    if(mAnswer4.getText().equals(mRight)){
                        Record.mRightCount++;
                        Toast.makeText(Listening.this, "TRUE", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Listening.this, "WRONG", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.play:
                    //播放音频
                    playMedia();
                    break;
            }
        }else{      //游戏结束
            Toast.makeText(Listening.this, "Game Over", Toast.LENGTH_SHORT).show();
            Toast.makeText(Listening.this, "Right Count :" + Record.mRightCount +
                            "Wrong Count :" + (Record.mWordCount - Record.mRightCount)
                    , Toast.LENGTH_SHORT).show();
            HomeFragment.mShowCont.setText(String.valueOf(Record.mRightCount));//主界面显示游戏完成进度
            Intent intent = new Intent(Listening.this, MainActivity.class);
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
                Intent intent = new Intent(Listening.this,Translation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(Listening.this,Spelling.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(Listening.this,Pictures.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(Listening.this,Listening.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
        }
    }


    //Play the Media
    public void playMedia() {
        mMediaPlayer = MediaPlayer.create(Listening.this,mMediaPath);

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //播放完毕，释放资源
                Listening.this.mMediaPlayer.release();
            }
        });

        if(mMediaPlayer!=null){
            mMediaPlayer.stop();    //先停止，就如prepare状态
        }

        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
