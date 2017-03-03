package com.example.asus.workking.ListviewBean;

/**
 * Created by asus on 2017/1/25.
 */

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.workking.R;

import java.util.List;

public class MyAdapter extends BaseAdapter{
    private List<ItemBean> mList;//数据源
    private LayoutInflater mInflater;//布局装载器对象

    // 通过构造方法将数据源与数据适配器关联起来
    // context:要使用当前的Adapter的界面对象
    public MyAdapter(Context context, List<ItemBean> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    //ListView需要显示的数据数量
    public int getCount() {
        return mList.size();
    }

    @Override
    //指定的索引对应的数据项
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    //指定的索引对应的数据项ID
    public long getItemId(int position) {
        return position;
    }

    @Override
    //返回每一项的显示内容
    public View getView(int position, View convertView, ViewGroup parent) {
        //将布局文件转化为View对象
        View view = mInflater.inflate(R.layout.menu_item_listview,null);

        /**
         * 找到item布局文件中对应的控件
         */
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView titleTextView = (TextView) view.findViewById(R.id.tv_title);
        TextView contentTextView = (TextView) view.findViewById(R.id.tv_content);

        //获取相应索引的ItemBean对象
        ItemBean bean = mList.get(position);

        /**
         * 设置控件的对应属性值
         */
        imageView.setImageResource(bean.itemImageResId);
        titleTextView.setText(bean.itemTitle);
        contentTextView.setText(bean.itemContent);

        return view;
    }
}