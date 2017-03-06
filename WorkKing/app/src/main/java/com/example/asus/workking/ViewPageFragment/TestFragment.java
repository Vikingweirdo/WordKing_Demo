package com.example.asus.workking.ViewPageFragment;

import android.app.Activity;
import android.content.Context;
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

import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/2/7.
 */

public class TestFragment extends Fragment implements View.OnClickListener{
    private String mTitle = "Defult";
    public final static String TITLE = "tilte";

    private List<Map> mTablesName = null;
    private Map<String,String> mName = null;

    private ImageButton mPictures = null;
    private ImageButton mListent = null;
    private ImageButton mTranslate = null;
    private ImageButton mSpell = null;

    private String mTempName = null;//用来保存游戏进度即将要读的表
    private int bookNum ;

    private String[] mTabKey = {
            "First",
            "Second",
            "Third",
            "Fourth",
            "Fifth",
            "Sixth",
            "Seventh",
            "eighth",
            "ninth",
            "tenth",
            "eleventh",
            " twelfth ",
            "thirteenth ",
            "fourteenth ",
            "fifteenth ",
            "sixteenth "
    };  //所有表的key

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toast.makeText(getActivity(),"Enter",Toast.LENGTH_SHORT).show();
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
        this.mTablesName = new ArrayList<>();//实例化集合


        this.mListent.setOnClickListener(this);
        this.mSpell.setOnClickListener(this);
        this.mTranslate.setOnClickListener(this);
        this.mPictures.setOnClickListener(this);

        //initMap();  //初始化匹配表
        //getTableKey(); //获取游戏记录中的表的key名字
    }

    private void initMap() {

        String session = "A";
        for (int i = 1; i <= 2; i++) {

            for (int j = 1; j <= 2; j++) {

                for (int k = 1; k <= 8; k++) {
                    String tablename = "book";
                    tablename += i;
                    tablename += "_";
                    tablename += k;
                    tablename += session;
                    System.out.println(tablename);
                }
                session = "B";
            }
            session = "A";
        }

    }

    //获取Key
    private void getTableKey() {
        SharedPreferences sharePreference = getActivity().getSharedPreferences(MainActivity.GAMEPROSS,
                Activity.MODE_PRIVATE); //获取操作对象
        this.mTempName = sharePreference.getString("record_unit","First");
        this.bookNum = sharePreference.getInt("record_book",1);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.listening:
                break;

            case R.id.spelling:
                break;

            case R.id.pictures:
                break;

            case R.id.translation:
                break;
        }
    }
}
