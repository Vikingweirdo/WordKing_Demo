package com.example.asus.workking.Tools;

/**
 * Created by asus on 2017/2/19.
 * 记录游戏的进度
 */

public class Record {
    public static int mRightCount ; //记录正确的个数
    public static int mGamePross ;  //记录游戏的进度

    public static boolean isEnd(){
        if (mRightCount == 50 ){
            return true;
        }
        return false;
    }
}
