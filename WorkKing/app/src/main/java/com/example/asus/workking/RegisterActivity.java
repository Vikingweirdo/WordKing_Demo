package com.example.asus.workking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.workking.Tools.RegsterThread;

public class RegisterActivity extends AppCompatActivity {

    //初始化组件
    private EditText mUsername = null;
    private EditText mPassword = null;
    private EditText mReconfim = null;
    private ImageButton mEnter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.register);

        //实例化组件
        this.mUsername = (EditText) super.findViewById(R.id.Rusername);
        this.mPassword = (EditText) super.findViewById(R.id.Rpassword);
        this.mReconfim = (EditText) super.findViewById(R.id.Rreconfim);
        this.mEnter = (ImageButton)super.findViewById(R.id.registerbtu);



        this.mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Username or password is empty",Toast.LENGTH_SHORT).show();
                    return;
                }else if (isEquals()){



                }else{
                    Toast.makeText(RegisterActivity.this,"Keep password equals",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            Intent inten = new Intent(this, LoginActivity.class);
            startActivity(inten);
            overridePendingTransition(R.anim.back_in_anim, R.anim.back_out_anim);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //判断空
    public boolean isEmpty() {
        if ("".equals(this.mUsername.getText().toString()) || "".equals(this.mPassword.getText().toString()) || "".equals(this.mReconfim.getText().toString())){
            return true;
        }
        return false;
    }

    //判断是否密码一致
    public boolean isEquals(){
        if (mPassword.getText().toString().equals(mReconfim.getText().toString())){
            return true;
        }
        return false;
    }
}
