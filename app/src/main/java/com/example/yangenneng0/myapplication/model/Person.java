package com.example.yangenneng0.myapplication.model;

import com.example.yangenneng0.myapplication.utils.PinYinUtils;

/**
 * User: yangenneng
 * DateTime: 2016/12/13 10:43
 * Description:好友实体类
 */
public class Person {
    //姓名
    private String name;
    //拼音
    private String pinyin;
    //拼音首字母
    private String headerWord;

    public Person(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinyin(name);
        headerWord = pinyin.substring(0, 1);
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        return headerWord;
    }
}
