package com.dengzi.yaoyiyao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class YaoYiYaoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mUserNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_yi_yao);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
    }

    private void initData() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        if (!TextUtils.isEmpty(userName)) {
            mUserNameTv.setText(userName);
        }
    }

    private void initListener() {
        mUserNameTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
