package com.example.asus.workking.BmobBean;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus on 2017/8/15.
 */

public class User extends BmobObject {
    private String name = null;
    private String pass = null;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getPass(){
        return this.pass;
    }
}
