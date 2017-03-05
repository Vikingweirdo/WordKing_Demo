package com.example.asus.workking.ViewPageFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.GameModel.Listening;
import com.example.asus.workking.GameModel.Pictures;
import com.example.asus.workking.GameModel.Spelling;
import com.example.asus.workking.GameModel.Translation;
import com.example.asus.workking.LoginActivity;
import com.example.asus.workking.MainActivity;
import com.example.asus.workking.R;
import com.example.asus.workking.Tools.InsertData;
import com.example.asus.workking.Tools.RandomModel;
import com.example.asus.workking.Tools.Record;
import com.example.asus.workking.Tools.Words;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/2/7.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private String mTitle = "Defult";
    public final static String TITLE = "tilte";


    //初始化界面
    private ImageButton book1 = null;
    private ImageButton book2 = null;
    private ImageButton book3 = null;
    private ImageButton book4 = null;
    private Toolbar mToolbar = null;
    private ImageButton mBegining = null;
    private TextView mShowUnit = null;
    public static  TextView mShowCont = null;

    //prepar activity list
    private RandomModel randomModel = new RandomModel();
    private List<Activity> mGameModle = new ArrayList<>();
    //Game model
    private Translation mTranslation = new Translation();
    private Pictures mPictures = new Pictures();
    private Listening mListening = new Listening();
    private Spelling mSpelling = new Spelling();


    //lock flag
    private String mUnit = null;
    private String mBookLock = null;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null){
            mTitle = getArguments().getString(TITLE);
        }
        //设置Fragment布局
        View view = inflater.inflate(R.layout.homepage, container, false);


        initView(view);



        return view;
    }

    private void initView(View view) {
        this.book1 = (ImageButton) view.findViewById(R.id.book1);
        this.book2 = (ImageButton) view.findViewById(R.id.book2);
        this.book3 = (ImageButton) view.findViewById(R.id.book3);
        this.book4 = (ImageButton) view.findViewById(R.id.book4);
        this.mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        this.mBegining = (ImageButton)view.findViewById(R.id.begging);
        this.mShowUnit = (TextView)view.findViewById(R.id.show_unit);
        this.mShowCont = (TextView)view.findViewById(R.id.show_cont);

        this.book1.setOnClickListener(this);
        this.book2.setOnClickListener(this);
        this.book3.setOnClickListener(this);
        this.book4.setOnClickListener(this);
        this.mBegining.setOnClickListener(this);

        //Add model
        this.mGameModle.add(mTranslation);
        this.mGameModle.add(mSpelling);
        this.mGameModle.add(mPictures);
        this.mGameModle.add(mListening);

        this.book2.setEnabled(false);
        this.book3.setEnabled(false);
        this.book4.setEnabled(false);

    }


    //弹出菜单显示
    private void showPopupWindow(View view, Drawable popwinBackGround) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(getActivity()).inflate(
                R.layout.popwindow, null);
        /*// 设置按钮的点击事件
        Button button = (Button) contentView.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button is pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(popwinBackGround);

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }


    @Override
    public void onClick(View view) {

        //找到弹出菜单的位置
        View poplocation = (Toolbar)getActivity().findViewById(R.id.toolbar);
        Drawable background = null;
        switch (view.getId()){

            case R.id.book1:
                background = getResources().getDrawable(R.drawable.popwindow_background);
                //弹出菜单
                showPopupWindow(poplocation,background);
                break;
            case R.id.book2:
                background = getResources().getDrawable(R.drawable.book2_back);
                //弹出菜单
                showPopupWindow(poplocation,background);
                break;
            case R.id.book3:
                background = getResources().getDrawable(R.drawable.book3_back);
                //弹出菜单
                showPopupWindow(poplocation,background);
                break;
            case R.id.book4:
                background = getResources().getDrawable(R.drawable.book4_back);
                //弹出菜单
                showPopupWindow(poplocation,background);
                break;
            case R.id.begging:
                Record.mGamePross= 1;
                Record.mWordCount = Words.getBook1_1A_words().length;//获取所有单词数
                RandomModel.mWordCount = Words.getBook1_1A_words().length;//获取所有单词数
                int position = randomModel.getModelSum();
                IntenActivity(position);//intent activity
                break;
        }

    }

    //Intent to Activity by position
    public void IntenActivity(int position){

        switch (position){

            case 0:
                Intent intent = new Intent(getActivity(),Translation.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                getActivity().finish();
                break;
            case 1:
                Intent intent1 = new Intent(getActivity(),Spelling.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                getActivity().finish();
                break;
            case 2:
                Intent intent2 = new Intent(getActivity(),Pictures.class);
                startActivity(intent2);
                getActivity().overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                getActivity().finish();
                break;
            case 3:
                Intent intent3 = new Intent(getActivity(),Listening.class);
                startActivity(intent3);
                getActivity().overridePendingTransition(R.anim.in_anim,R.anim.out_anim);
                getActivity().finish();
                break;
        }
    }
}
