package com.example.asus.workking.GameModel;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;

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
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.mAnswer3.setText(MainActivity.mWord);
        this.mMediaPath = MainActivity.mMediaPath;
    }

    @Override
    public void onClick(View view) {
        if(!Record.isEnd()) {

            int position;
            switch (view.getId()) {
                case R.id.answer1:
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    Toast.makeText(Listening.this, "TRUE", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.answer2:
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    break;

                case R.id.answer3:
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    break;

                case R.id.answer4:
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    break;

                case R.id.play:
                    //播放音频
                    playMedia();
                    break;
            }
        }else{      //游戏结束

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
