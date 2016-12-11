package com.example.yangenneng0.myapplication.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.yangenneng0.myapplication.MainActivity;
import com.example.yangenneng0.myapplication.R;

/**
 * User: yangenneng
 * DateTime: 2016/12/10 16:31
 * Description:发送短信
 */
public class MessageActivity extends Activity {

    private AutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String telPhone="18468194784";
        textView= (AutoCompleteTextView) findViewById(R.id.sharePerson);//联系人

        Button button= (Button) findViewById(R.id.sendMSM);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message="Hi,我正在使用YEN的手机QQ，你也来试试吧~~~~";
                String persons=textView.getText().toString();
                String[] personArr=persons.split(",");//逗号分隔
                for ( String telPhone:personArr ){
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(telPhone, null, message, null, null);
                        Toast.makeText(getApplicationContext(), telPhone+"短信发送成功", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),telPhone+"短信发送失败.",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }


                Intent intent=new Intent();
                intent.setClass(MessageActivity.this,MainActivity.class);
                MessageActivity.this.startActivity(intent);

            }
        });



    }
}
