package com.example.demo.pojo;

public class ImageType {
    private int ty_id;
    private String ty_name;//类型名称
    private String ty_description;//类型描述
    private int ty_ur_id;//用户id

    /* getter and setter */
    public int getTy_id(){ return ty_id; }
    public void setTy_id(int ty_id){
        this.ty_id=ty_id;
    }

    public String getITy_name(){
        return ty_name;
    }
    public void setTy_name(String ty_name){
        this.ty_name=ty_name;
    }

    public String getTy_description(){
        return ty_description;
    }
    public void setTy_description(String ty_description){
        this.ty_description=ty_description;
    }

    public int getTy_ur_id(){
        return ty_ur_id;
    }
    public void setTy_ur_id(int ty_ur_id){
        this.ty_ur_id=ty_ur_id;
    }

}
