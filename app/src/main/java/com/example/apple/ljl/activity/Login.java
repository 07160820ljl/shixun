package com.example.apple.ljl.activity;

/**
 * Created by apple on 2017/12/27.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.ljl.LjlActivity;
import com.example.apple.ljl.R;
import com.example.apple.ljl.dao.PwdDAO;


public class Login extends Activity {
    private EditText txtlogin;
    private Button btnlogin, btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtlogin = (EditText) findViewById(R.id.tx_login);
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnclose = (Button) findViewById(R.id.btn_close);

        btnlogin.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Login.this, LjlActivity.class);
                PwdDAO pwdDAO = new PwdDAO(Login.this);
                // 判断是否有密码及是否输入了密码
                if ((pwdDAO.getCount() == 0 || pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString().isEmpty()) {
                    startActivity(intent);// 启动主Activity
                } else {
                    // 判断输入的密码是否与数据库中的密码一致
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())) {
                        startActivity(intent);// 启动主Activity
                    } else {
                        // 弹出信息提示
                        Toast.makeText(Login.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText("");// 清空密码文本框
            }
        });

        btnclose.setOnClickListener(new OnClickListener() {// 为取消按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                finish();// 退出当前程序
            }
        });
    }
}