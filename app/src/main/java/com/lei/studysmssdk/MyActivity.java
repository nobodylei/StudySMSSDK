package com.lei.studysmssdk;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by yanle on 2018/3/11.
 */

public class MyActivity extends Activity implements View.OnClickListener{
    private TextView tv_country;
    private EditText et_phone, et_sms;
    private Button btn_sub, btn_sms;
    private LinearLayout sms_layout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        init();
        btn_sub.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
    }

    public void init() {
        Toast.makeText(MyActivity.this, "init()", Toast.LENGTH_SHORT).show();
        tv_country = findViewById(R.id.tv_country);
        et_phone = findViewById(R.id.et_phone);
        et_sms = findViewById(R.id.et_sms);
        btn_sms = findViewById(R.id.btn_sms);
        btn_sub = findViewById(R.id.btn_sub);
        sms_layout = findViewById(R.id.sms_layout);
    }
    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //showToast("发送成功");
                    //Toast.makeText(MyActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                } else{
                    // TODO 处理错误的结果
                    showToast("发送失败");
                    //Toast.makeText(MyActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    showToast("验证成功");
                    //Toast.makeText(MyActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    // TODO 处理验证成功的结果
                } else{
                    // TODO 处理错误的结果
                    //Toast.makeText(MyActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                    showToast("验证失败");
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @Override
    public void onClick(View v) {
        String country = "86";
        String phone = et_phone.getText() + "";
        String code = null;
        switch (v.getId()) {
            case R.id.btn_sub:
                sendCode(country, phone);
                sms_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_sms:
                code = et_sms.getText() + "";
                submitCode(country, phone, code);
                break;
        }
    }

    public void showToast(String message) {
        final String str = message;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
