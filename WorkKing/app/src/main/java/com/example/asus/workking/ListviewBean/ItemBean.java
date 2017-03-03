package com.example.asus.workking.ListviewBean;

/**
 * Created by asus on 2017/1/25.
 */

public class ItemBean {
    public int itemImageResId;//图像资源ID
    public String itemTitle;//标题
    public String itemContent;//内容

    public ItemBean(int itemImageResId, String itemTitle, String itemContent) {
        this.itemImageResId = itemImageResId;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
