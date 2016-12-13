package com.example.yangenneng0.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.yangenneng0.myapplication.adapter.PersonAdapter;
import com.example.yangenneng0.myapplication.model.Person;
import com.example.yangenneng0.myapplication.utils.CameraActivity;
import com.example.yangenneng0.myapplication.utils.MapActivity;
import com.example.yangenneng0.myapplication.utils.MessageActivity;
import com.example.yangenneng0.myapplication.utils.WordsNavigation;
import com.example.yangenneng0.myapplication.viewUI.QQmoodActivity;
import com.example.yangenneng0.myapplication.viewUI.QzoneActivity;
import com.example.yangenneng0.myapplication.viewUI.RegistActivity;
import com.example.yangenneng0.myapplication.viewUI.SettingsActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 功能：软件主界面类
 */
public class MainActivity extends AppCompatActivity
        implements  WordsNavigation.onWordsChangeListener,
        NavigationView.OnNavigationItemSelectedListener , AbsListView.OnScrollListener{
    //侧拉菜单滑出来的那一部分属于NavigationView


    //---联系人列表属性
    private Handler handler;
    private List<Person> list;
    private TextView tv;
    private ListView listView;
    private WordsNavigation word;
    //---联系人列表属性

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //顶部工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //--------------联系人
        tv = (TextView) findViewById(R.id.tv);
        word = (WordsNavigation) findViewById(R.id.words);
        listView = (ListView) findViewById(R.id.list);
        //--------------联系人
        //初始化数据
        initData();
        //初始化列表
        initListView();
        //设置列表点击滑动监听
        handler = new Handler();
        word.setOnWordsChangeListener(this);
        //--------------联系人


        ////悬浮按钮
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        //界面底部弹出信息 比toast更加好,毕竟snackbar 可以响应点击事件
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        //Android官方侧滑菜单DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle监听Drawer拉出、隐藏
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设置左侧菜单
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ////聊天
        //Button button= (Button) findViewById(R.id.goChat);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent();
        //        intent.setClass(MainActivity.this, ChatMainActivity.class);
        //        MainActivity.this.startActivity(intent);
        //    }
        //});

        //发表说说
        FloatingActionButton button= (FloatingActionButton) findViewById(R.id.button_mood);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QQmoodActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //浏览空间
        FloatingActionButton button2= (FloatingActionButton) findViewById(R.id.button_qzone);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QzoneActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    /**
     * 取Back键的按下事件
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ( drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);  //如果左侧导航打开，单机Back键时关闭左侧导航
        } else {
            super.onBackPressed();//如果左侧导航关闭，，单机Back键时退出程序
        }
    }

    /**
     * 用于初始化菜单 (只会在第一次初始化菜单时调用）
     *
     * @param menu 即将要显示的Menu实例
     * @return 返回true则显示该menu, false 则不显示
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        if ( id == R.id.action_regist ) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RegistActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 左侧导航被选择时触发的事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_camera ) {//拍照
            // Handle the camera action
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,CameraActivity.class);
            MainActivity.this.startActivity(intent);
        } else if ( id == R.id.nav_gallery ) {  //相册

            //Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
            //intent1.addCategory(Intent.CATEGORY_OPENABLE);
            //intent1.setType("image/*");
            //if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT ) {
            //    startActivityForResult(intent1, 3);
            //} else {
            //    startActivityForResult(intent1, 3);
            //}

            Intent intent = new Intent(Intent.ACTION_VIEW);
            String type = "image/*";
            Uri uri = Uri.parse("file:///sdcard/QQ_Test/");
            intent.setDataAndType(uri, type);
            startActivity(intent);

        } else if ( id == R.id.nav_slideshow ) {  //视频

            Intent intent = new Intent(Intent.ACTION_VIEW);
            String type = "video/mp4";
            Uri uri = Uri.parse("file:///sdcard/QQ_TEST_Video/03简单句的补充.mp4");
            intent.setDataAndType(uri, type);
            startActivity(intent);

        } else if ( id == R.id.nav_manage ) {  //设置
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(intent);
        } else if ( id == R.id.nav_share ) {  //分享
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MessageActivity.class);
            MainActivity.this.startActivity(intent);

        } else if ( id == R.id.nav_send ) {  //位置
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MapActivity.class);
            MainActivity.this.startActivity(intent);
        }

        //选择之后关闭左侧导航
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //继承 WordsNavigation.onWordsChangeListener
    //手指按下字母改变监听回调
    @Override
    public void wordsChange(String words) {
        updateWord(words);
        updateListView(words);
    }

    private void initListView() {
        PersonAdapter adapter = new PersonAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("Dave"));
        list.add(new Person("张晓飞"));
        list.add(new Person("杨光福"));
        list.add(new Person("阿钟"));
        list.add(new Person("胡继群"));
        list.add(new Person("徐歌阳"));
        list.add(new Person("钟泽兴"));
        list.add(new Person("宋浮点"));
        list.add(new Person("刘俄式"));
        list.add(new Person("Tony"));
        list.add(new Person("老刘"));
        list.add(new Person("葛优"));
        list.add(new Person("安传鑫"));
        list.add(new Person("温松"));
        list.add(new Person("成龙"));
        list.add(new Person("那英"));
        list.add(new Person("刘甫"));
        list.add(new Person("沙宝亮"));
        list.add(new Person("张梁"));
        list.add(new Person("张大爷"));
        list.add(new Person("张哥"));
        list.add(new Person("张娃"));
        list.add(new Person("张丽"));
        list.add(new Person("吴亮"));
        list.add(new Person("Tom"));
        list.add(new Person("安在"));
        list.add(new Person("小李"));
        list.add(new Person("贝阿朵"));
        list.add(new Person("赵二喜"));
        list.add(new Person("阿道夫"));
        list.add(new Person("姜宇航"));

        //对集合排序
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动列表的时候，更新右侧字母列表的选中状态
        word.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < list.size(); i++) {
            String headerWord = list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                listView.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        tv.setText(words);
        tv.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //1s后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setVisibility(View.GONE);
            }
        }, 500);
    }

}
