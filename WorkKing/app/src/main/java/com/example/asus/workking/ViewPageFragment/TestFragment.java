package com.example.asus.workking.ViewPageFragment;

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
        this.mTablesName = new ArrayList<>();
        

        this.mListent.setOnClickListener(this);
        this.mSpell.setOnClickListener(this);
        this.mTranslate.setOnClickListener(this);
        this.mPictures.setOnClickListener(this);

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
