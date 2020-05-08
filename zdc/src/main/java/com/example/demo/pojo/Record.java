package com.example.demo.pojo;

import java.util.Date;

public class Record {
    private int re_id;
    private String re_word;//单词
    private Date re_date;//日期
    private int re_level;//熟练度
    private int re_ur_id;//用户id

    /* getter and setter */
    public int getRe_id(){ return re_id; }
    public void setRe_id(int re_id){
        this.re_id=re_id;
    }

    public String getRe_word(){ return re_word; }
    public void setRe_word(String re_word){
        this.re_word=re_word;
    }

    public Date getRe_date(){ return re_date; }
    public void setRe_date(Date re_date){
        this.re_date=re_date;
    }

    public int getRe_level(){ return re_level; }
    public void setRe_level(int re_level){
        this.re_level=re_level;
    }

    public int getRe_ur_id(){ return re_ur_id; }
    public void setRe_ur_id(int re_ur_id){
        this.re_ur_id=re_ur_id;
    }
}
