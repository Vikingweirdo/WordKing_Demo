package com.example.asus.workking;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by asus on 2017/2/8.
 */

public class ChangeColorIconWithText extends View {

    private int mColor = 0xFF45C01A;
    private Bitmap mIconBitmap = null;
    private String mText = "记词王";
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
            12, getResources().getDisplayMetrics());


    private Canvas mCanvas = null;
    private Bitmap mBitmap = null;
    private Paint mPaint = null;

    private float mAlpha ;

    private Rect mIconRect = null;
    private Rect mTextBound = null;

    private Paint mTextPaint = null;





    public ChangeColorIconWithText(Context context) {
        this(context, null);
    }

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    /*
    * 获取自定义属性的值
    * */
    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ChangeColorIconWithText);

        int n = a.getIndexCount();

        for(int i = 0 ; i < n ; i++){

            int attr = a.getIndex(i);
            switch (attr){

                case R.styleable.ChangeColorIconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable)a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;

                case R.styleable.ChangeColorIconWithText_color:
                    mColor = a.getColor(attr,0xFF45C01A);
                    break;
                case R.styleable.ChangeColorIconWithText_text:
                    mText = a.getString(attr);
                break;

                case R.styleable.ChangeColorIconWithText_text_size:
                    mTextSize = (int)(a.getDimension(attr,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            12, getResources().getDisplayMetrics())));

                    break;
            }
        }

        a.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);

        mTextPaint.getTextBounds(mText,0,mText.length(),mTextBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),
                getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-mTextBound.height()
                );

        int left = getMeasuredWidth()/2-iconWidth/2;
        int top = getMeasuredHeight()/2 - (mTextBound.height()+iconWidth)/2;

        mIconRect = new Rect(left,top,left+iconWidth,top+iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap,null,mIconRect,null);

        int alpha = (int)Math.ceil(255*mAlpha);

        setupTargetBitmap(alpha);

        canvas.drawBitmap(mBitmap,0,0,null);
    }

    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect,mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap,null,mIconRect,mPaint);
    }

    public void setIconAlpha(float alpha){
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        }else {
            postInvalidate();
        }
    }
}
