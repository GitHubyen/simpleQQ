package com.example.yangenneng0.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * User: yangenneng
 * DateTime: 2016/12/10 13:58
 * Description:欢迎页面
 */
public class WelcomeActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Intent intent=new Intent();
        intent.setClass(WelcomeActivity.this,LoginActivity.class);

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };

        timer.schedule(timerTask,2000);//此处的Delay可以是3*1000，代表三秒

    }

}
