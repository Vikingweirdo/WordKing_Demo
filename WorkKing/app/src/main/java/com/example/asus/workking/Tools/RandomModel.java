package com.example.asus.workking.Tools;

import java.util.Random;

/**
 * Created by asus on 2017/2/11.
 */

public class RandomModel  {
    private final static int MODEL_COUNT = 4;//random model count
    private Random random = null;
    public RandomModel(){
        this.random = new Random();
    }

    public int getModelSum(){
        return random.nextInt(MODEL_COUNT)+1;
    }
}
