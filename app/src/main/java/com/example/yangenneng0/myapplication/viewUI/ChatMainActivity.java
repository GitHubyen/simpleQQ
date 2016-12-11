package com.example.yangenneng0.myapplication.viewUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.yangenneng0.myapplication.MainActivity;
import com.example.yangenneng0.myapplication.R;
import com.example.yangenneng0.myapplication.adapter.ChatMsgViewAdapter;
import com.example.yangenneng0.myapplication.model.ChatMsgEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: yangenneng
 * DateTime: 2016/12/11 17:11
 * Description:
 */
public class ChatMainActivity extends Activity implements View.OnClickListener {

    private Button mBtnSend;// 发送btn
    private Button mBtnBack;// 返回btn
    private EditText mEditTextContent;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmain);

        initView();// 初始化view
        initData();// 初始化数据
        mListView.setSelection(mAdapter.getCount() - 1);

    }

    /**
     * 初始化view
     */
    public void initView() {
        mListView= (ListView) findViewById(R.id.listview);
        mBtnSend=(Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.chatGoHome);
        mBtnBack.setOnClickListener(this);
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private String[] msgArray = new String[] { "在吗，有时间吗", "有！你呢？", "我也有", "那上吧",
            "打啊！你放大啊！", "你咋不放呢？留大抢人头啊？", "不解释", "尼滚...",
            "今晚去网吧吧？", "确定吗？", "不然呢？", "OK,走起！！" };

    private String[] dataArray = new String[] { "2016-09-22 18:00:02",
            "2016-09-22 18:10:22", "2016-09-22 18:11:24",
            "2016-09-22 18:20:23", "2016-09-22 18:30:31",
            "2016-09-22 18:35:37", "2016-09-22 18:40:13",
            "2016-09-22 18:50:26", "2016-09-22 18:52:57",
            "2016-09-22 18:55:11", "2016-09-22 18:56:45",
            "2016-09-22 18:57:33", };
    private final static int COUNT = 12;// 初始化数组总数

    /**
     * 模拟加载消息历史，实际开发可以从数据库中读出
     */
    public void initData() {
        for (int i = 0; i < COUNT; i++) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName("LLL");
                entity.setMsgType(true);// 收到的消息
            } else {
                entity.setName("YEN");
                entity.setMsgType(false);// 自己发送的消息
            }
            entity.setMessage(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:// 发送按钮点击事件
                send();
                break;
            case R.id.chatGoHome:// 返回按钮点击事件
                Intent intent = new Intent();
                intent.setClass(ChatMainActivity.this, MainActivity.class);
                ChatMainActivity.this.startActivity(intent);
                break;
        }
    }


    /**
     * 发送消息
     */
    private void send() {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setName("YEN");
            entity.setDate(getDate());
            entity.setMessage(contString);
            entity.setMsgType(false);

            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

            mEditTextContent.setText("");// 清空编辑框数据
            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项

        }
    }


    /**
     * 发送消息时，获取当前事件
     * @return 当前时间
     */
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

}
