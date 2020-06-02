package com.example.demo.pojo;

public class Finish {
    private int fi_id;//完成id
    private int fi_count;//单词数量
    private int fi_ur_id;//用户id

    /* getter and setter */
    public int getFi_id(){ return fi_id; }
    public void setFi_id(int fi_id){
        this.fi_id=fi_id;
    }

    public int getFi_count(){ return fi_count; }
    public void setFi_count(int fi_count){
        this.fi_count=fi_count;
    }

    public int getFi_ur_id(){ return fi_ur_id; }
    public void setFi_ur_id(int fi_ur_id){
        this.fi_ur_id=fi_ur_id;
    }
}
