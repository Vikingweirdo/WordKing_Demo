package com.example.asus.workking.GameModel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.ViewPageFragment.HomeFragment;

public class Spelling extends AppCompatActivity implements View.OnClickListener{

    private Button mEnter = null;
    private EditText mInput = null;
    private TextView mWordMean = null;

    private RandomModel mRandom = null;

    private String mRightAnswer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_spelling);

        initView();
    }

    private void initView() {
        this.mEnter = (Button)super.findViewById(R.id.enter);
        this.mWordMean = (TextView)super.findViewById(R.id.wordmean);
        this.mInput = (EditText)super.findViewById(R.id.splling_input);

        this.mRandom = new RandomModel();

        this.mEnter.setOnClickListener(this);

        loadData();//load game data
    }

    private void loadData() {

        System.out.println("数据长度" + Record.mGamePross);
        //search databases
        MainActivity.getPositionData(Record.mGamePross);
        this.mWordMean.setText(MainActivity.mWordMean + "(注意大小写)");
        mRightAnswer = MainActivity.mWord;
    }

    @Override
    public void onClick(View view) {

        if(Record.mGamePross!=Record.mWordCount) {

            Record.mGamePross++;

            int position;

            if(mInput.getText().toString().equals(mRightAnswer)){
                Toast.makeText(Spelling.this,"right answer",Toast.LENGTH_SHORT).show();
                Record.mRightCount++;
            }else{
                Toast.makeText(Spelling.this,"wrong answer",Toast.LENGTH_SHORT).show();
            }

            position = mRandom.getModelSum();
            IntenActivity(position);

        }else{      //游戏结束
            Toast.makeText(Spelling.this, "Game Over", Toast.LENGTH_SHORT).show();
            Toast.makeText(Spelling.this, "Right Count :" + Record.mRightCount +
                            "Wrong Count :" + (Record.mWordCount - Record.mRightCount)
                    , Toast.LENGTH_SHORT).show();
            HomeFragment.mShowCont.setText(String.valueOf(Record.mRightCount));//主界面显示游戏完成进度
            Intent intent = new Intent(Spelling.this, MainActivity.class);
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
                Intent intent = new Intent(Spelling.this,Translation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(Spelling.this,Spelling.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(Spelling.this,Pictures.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(Spelling.this,Listening.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                finish();
                break;
        }
    }
}
