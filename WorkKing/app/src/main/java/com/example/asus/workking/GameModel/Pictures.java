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

public class Pictures extends AppCompatActivity implements View.OnClickListener{
    private Button answer1 = null;
    private Button answer2 = null;
    private Button answer3 = null;
    private Button answer4 = null;
    private TextView word = null;

    private RandomModel mRandom = null;     //产生随机数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pictures);


        initView();
    }

    private void initView() {
        this.answer1 = (Button)super.findViewById(R.id.answer1);
        this.answer2 = (Button)super.findViewById(R.id.answer2);
        this.answer3 = (Button)super.findViewById(R.id.answer3);
        this.answer4 = (Button)super.findViewById(R.id.answer4);
        this.word = (TextView)super.findViewById(R.id.word);

        this.mRandom = new RandomModel();

        this.answer1.setOnClickListener(this);
        this.answer2.setOnClickListener(this);
        this.answer3.setOnClickListener(this);
        this.answer4.setOnClickListener(this);



        loadData(); //loading game data

    }

    private void loadData() {
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.word.setText(MainActivity.mWord);
        this.answer2.setBackgroundResource(MainActivity.mPicturePath);
        this.answer1.setBackgroundResource(R.drawable.pledge);
        this.answer3.setBackgroundResource(R.drawable.pledge);
        this.answer4.setBackgroundResource(R.drawable.pledge);

        System.out.println("imagepath = "+MainActivity.mPicturePath);
    }

    @Override
    public void onClick(View view) {
        if(!Record.isEnd()) {

            int position;
            switch (view.getId()) {
                case R.id.answer1:
                    position = mRandom.getModelSum();
                    IntenActivity(position);//intent activity
                    Toast.makeText(Pictures.this, "TRUE", Toast.LENGTH_SHORT).show();
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
            }
        }else{      //游戏结束

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
