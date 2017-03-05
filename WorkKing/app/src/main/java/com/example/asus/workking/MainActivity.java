package com.example.asus.workking;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.example.asus.workking.Database.DBHelper;
import com.example.asus.workking.GameModel.Listening;
import com.example.asus.workking.GameModel.Pictures;
import com.example.asus.workking.Tools.InsertData;
import com.example.asus.workking.Tools.MyDialog;
import com.example.asus.workking.Tools.Words;
import com.example.asus.workking.ViewPageFragment.HomeFragment;
import com.example.asus.workking.ViewPageFragment.MeFragment;
import com.example.asus.workking.ViewPageFragment.RankFragment;
import com.example.asus.workking.ViewPageFragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static String TABLENAME = "book1_1A";  //operated table name
    public final static String FILENAME = "loadflag";
    public final static String GAMEPROSS = "gamepross";

    public static SharedPreferences mSharePreferences = null;


    private ViewPager mViewPager = null;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapt = null;
    private List<ChangeColorIconWithText> mTabIndicator = new ArrayList<>();


    //SQLiteDatabases Object
    private static SQLiteDatabase mSqLiteDatabase = null;
    public static DBHelper mDbHelper = null;

    //从数据库取数据填装变量
    public static String mWord = null;
    public static int mPicturePath;
    public static int mMediaPath;
    public static String mWordMean = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main);

        loadFlag_data();     //取SharedPreferences数据
        initView(); //实例化各个组件
        //初始化viewpager
        initData();
        mViewPager.setAdapter(mAdapt);
        initEvent();

    }


    //取标志
    private void loadFlag_data() {
        if(mSharePreferences == null){
            mSharePreferences = super.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
            System.out.println("文件取数据"+mSharePreferences.getInt("loadFlag_data",1));
        }

        if(mSharePreferences.getInt("loadFlag",1)!=1) {


            inintTables();//生成表

            //插入数据
            InsertData insertData = new InsertData();
            insertData.setmSqLiteDatabase(mSqLiteDatabase);
            insertData.setmTableName(TABLENAME);
            insertData.insertData();

            //修改读取flag
            SharedPreferences.Editor editor = mSharePreferences.edit();
            editor.putInt("loadFlag", 1);
            editor.commit();
        }
    }



    public static void getPositionData(int position) {
        mSqLiteDatabase = mDbHelper.getReadableDatabase();
        Cursor cursor = mSqLiteDatabase.query(TABLENAME,null, null, null, null, null, null);

        cursor.move(position);


        mWord = cursor.getString(cursor.getColumnIndex("word"));
        mPicturePath = cursor.getInt(cursor.getColumnIndex("imagepath"));
        mMediaPath = cursor.getInt(cursor.getColumnIndex("mediapath"));
        mWordMean = cursor.getString(cursor.getColumnIndex("wordmean"));


    }

    //Init databases tables
    private void inintTables() {

        this.mSqLiteDatabase = mDbHelper.getWritableDatabase();
        String session = "A";
        for (int i = 1; i <= 2; i++) {

            for (int j = 1; j <= 2; j++) {

                for (int k = 1; k <= 8; k++) {
                    String tablename = "book";
                    tablename += i;
                    tablename += "_";
                    tablename += k;
                    tablename += session;
                    mDbHelper.createTable(mSqLiteDatabase, tablename);
                    System.out.println(tablename);
                }
                session = "B";
            }
            session = "A";
        }
        mSqLiteDatabase = mDbHelper.getReadableDatabase();

    }

    /*
    * 初始化事件
    * */
    private void initEvent() {
        mViewPager.setOnPageChangeListener(this);
    }

    private void initData() {
        /*for(String title : mTitles){
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(HomeFragment.TITLE,title);
            homeFragment.setArguments(bundle);
            mTabs.add(homeFragment);
        }*/


        //add fragment
        HomeFragment homeFragment = new HomeFragment();
        mTabs.add(homeFragment);
        TestFragment testFragment = new TestFragment();
        mTabs.add(testFragment);
        MeFragment meFragment = new MeFragment();
        mTabs.add(meFragment);
        RankFragment rankFragment = new RankFragment();
        mTabs.add(rankFragment);


        mAdapt = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }

    private void initView() {
        mSqLiteDatabase = mDbHelper.getWritableDatabase();//实例化数据库操作对象

        mViewPager = (ViewPager) super.findViewById(R.id.viewpage);


        ChangeColorIconWithText one = (ChangeColorIconWithText) super.findViewById(R.id.id_indicator_one);
        mTabIndicator.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) super.findViewById(R.id.id_indicator_two);
        mTabIndicator.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) super.findViewById(R.id.id_indicator_three);
        mTabIndicator.add(three);
        ChangeColorIconWithText fouth = (ChangeColorIconWithText) super.findViewById(R.id.id_indicator_fouth);
        mTabIndicator.add(fouth);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        fouth.setOnClickListener(this);


        one.setIconAlpha(1.0f);
    }


    @Override
    public void onClick(View view) {
        resetIconAlph();

        switch (view.getId()) {

            case R.id.id_indicator_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_fouth:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;


        }
    }

    /*
    * 重置其他ICON颜色
    *
    * */
    private void resetIconAlph() {

        for (int i = 0; i < mTabIndicator.size(); i++) {

            mTabIndicator.get(i).setIconAlpha(0);   //设置透明度
        }
    }

    //viewPager滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //通过positionOffset值得变化达到渐变的效果
        if (positionOffset > 0) {
            ChangeColorIconWithText left = mTabIndicator.get(position);
            ChangeColorIconWithText right = mTabIndicator.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }


    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }




    //先new出一个监听器，设置好监听
    private DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){
                case Dialog.BUTTON_POSITIVE:
                    finish();
                    break;
                //case Dialog.BUTTON_NEGATIVE:

                //   break;
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    //返回键的监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            MyDialog myDialog = null;
            myDialog = new MyDialog(MainActivity.this);
            myDialog.showDialog(dialogOnclicListener);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
