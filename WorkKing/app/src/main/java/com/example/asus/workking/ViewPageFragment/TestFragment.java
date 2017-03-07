package com.example.asus.workking.ViewPageFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.asus.workking.GameModel.Listening;
import com.example.asus.workking.GameModel.Pictures;
import com.example.asus.workking.GameModel.Spelling;
import com.example.asus.workking.GameModel.Translation;
import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.Tools.Words;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/2/7.
 */

public class TestFragment extends Fragment implements View.OnClickListener{
    private String mTitle = "Defult";
    public final static String TITLE = "tilte";



    private ImageButton mPictures = null;
    private ImageButton mListent = null;
    private ImageButton mTranslate = null;
    private ImageButton mSpell = null;
    private Intent mIntent = null;


    private String mTempName = null;//用来保存游戏进度即将要读的表
    private int bookNum = 1 ;   //默认第一本书

    private String[] mTabKey = {
            "First1A",
            "Second2A",
            "Third3A",
            "Fourth4A",
            "Fifth5A",
            "Sixth6A",
            "Seventh7A",
            "eighth8A",
            "First1B",
            "Second2B",
            "Third3B",
            "Fourth4B",
            "Fifth5B",
            "Sixth6B",
            "Seventh7B",
            "eighth8B"

    };  //所有表的key

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null){
            mTitle = getArguments().getString(TITLE);
        }
        //设置Fragment布局
        View view = inflater.inflate(R.layout.testpage, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        this.mListent = (ImageButton)view.findViewById(R.id.listening);
        this.mSpell = (ImageButton)view.findViewById(R.id.spelling);
        this.mTranslate = (ImageButton)view.findViewById(R.id.translation);
        this.mPictures = (ImageButton)view.findViewById(R.id.pictures);


        this.mListent.setOnClickListener(this);
        this.mSpell.setOnClickListener(this);
        this.mTranslate.setOnClickListener(this);
        this.mPictures.setOnClickListener(this);

        getTableKey(); //获取游戏记录中的表的key名字
    }


    //获取Key
    private void getTableKey() {
        SharedPreferences sharePreference = getActivity().getSharedPreferences(MainActivity.GAMEPROSS,
                Activity.MODE_PRIVATE); //获取操作对象
        this.mTempName = sharePreference.getString("record_unit","First1A");
        this.bookNum = sharePreference.getInt("record_book",1);

        setTable();//设置要查询的表
    }

    private void setTable() {
        String file = null;
        SharedPreferences sharePre = null;
        switch (this.bookNum){
            case 1:
                file = "book1";
                sharePre = getActivity().getSharedPreferences(file,Activity.MODE_PRIVATE);
                MainActivity.TABLENAME = sharePre.getString(mTempName,"First1A");
                break;
            case 2:
                file = "book2";
                sharePre = getActivity().getSharedPreferences(file,Activity.MODE_PRIVATE);
                MainActivity.TABLENAME = sharePre.getString(mTempName,"First1A");
                break;
            case 3:
                file = "book3";
                sharePre = getActivity().getSharedPreferences(file,Activity.MODE_PRIVATE);
                MainActivity.TABLENAME = sharePre.getString(mTempName,"First1A");
                break;
            case 4:
                file = "book4";
                sharePre = getActivity().getSharedPreferences(file,Activity.MODE_PRIVATE);
                MainActivity.TABLENAME = sharePre.getString(mTempName,"First1A");
                break;
        }
    }


    @Override
    public void onClick(View view) {
        Record.mGamePross= 1;
        Record.mWordCount = Words.getBook1_1A_words().length;//获取所有单词数
        RandomModel.mWordCount = Words.getBook1_1A_words().length;//获取所有单词数

        switch (view.getId()){
            case R.id.listening:
                this.mIntent = new Intent(getActivity(), Listening.class);
                Bundle bundle = new Bundle();
                bundle.putInt("modleFlag",0x111);   //判断是否是测试模式
                mIntent.putExtras(bundle);
                startActivity(mIntent);
                getActivity().overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                getActivity().finish();
                break;

            case R.id.spelling:
                this.mIntent = new Intent(getActivity(), Spelling.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("modleFlag",0x111);   //判断是否是测试模式
                mIntent.putExtras(bundle1);
                startActivity(mIntent);
                getActivity().overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                getActivity().finish();
                break;

            case R.id.pictures:
                this.mIntent = new Intent(getActivity(), Pictures.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("modleFlag",0x111);   //判断是否是测试模式
                mIntent.putExtras(bundle2);
                startActivity(mIntent);
                getActivity().overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                getActivity().finish();
                break;

            case R.id.translation:
                this.mIntent = new Intent(getActivity(), Translation.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("modleFlag",0x111);   //判断是否是测试模式
                mIntent.putExtras(bundle3);
                startActivity(mIntent);
                getActivity().overridePendingTransition(R.anim.in_anim, R.anim.out_anim);
                getActivity().finish();
                break;
        }
    }
}
