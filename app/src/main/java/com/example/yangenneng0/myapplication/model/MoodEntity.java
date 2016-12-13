package com.example.yangenneng0.myapplication.model;

/**
 * User: yangenneng
 * DateTime: 2016/12/13 19:57
 * Description:说说实体类
 */
public class MoodEntity {
    private String content;
    private String time;

    public MoodEntity(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
