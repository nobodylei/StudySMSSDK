package com.lei.studysmssdk;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_bind_phone);
        //初始化Sdk
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
                //sendCode(MainActivity.this);
                //注册手机号
//                RegisterPage registerPage = new RegisterPage();
//                //注册回调事件
//
//                //显示注册界面
//                registerPage.show(MainActivity.this);
            }
        });
    }




   /*  public void sendCode(Context context) {
        //RegisterPage page = new RegisterPage();

       page.setRegisterCallback(new EventHandler() {
            //事件完成后调用
            public void afterEvent(int event, int result, Object data) {
                Toast.makeText(MainActivity.this,"验证", Toast.LENGTH_SHORT).show();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                    submitUserInfo(country, phone);
                    Toast.makeText(MainActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                } else{
                    // TODO 处理错误的结果
                    Toast.makeText(MainActivity.this, "验证码错误",Toast.LENGTH_SHORT).show();
                }
            }
       /});
        page.show(context);
    }
    //提交用户信息
    public void submitUserInfo(String country, String phone) {
        String uid = (int)(Math.random() * 100) + "";
        String userName = "imooc";
        SMSSDK.submitUserInfo(uid, userName, null, country, phone);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //SMSSDK.unregisterAllEventHandler();
    }
}
