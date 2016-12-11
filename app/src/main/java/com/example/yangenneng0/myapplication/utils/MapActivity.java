package com.example.yangenneng0.myapplication.utils;

import android.app.Activity;
import android.os.Bundle;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.example.yangenneng0.myapplication.R;

/**
 * User: yangenneng
 * DateTime: 2016/12/11 10:08
 * Description:
 * 由于SDK并没有提供用于管理地图生命周期的Activity，因此需要用户继承Activity后管理地图的生命周期，防止内存泄露
 */
public class MapActivity  extends Activity   {

    AMap aMap= null;
    MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);

        if(aMap==null){
            aMap = mMapView.getMap();//由MapView实例化AMap  //初始化地图变量
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


}
