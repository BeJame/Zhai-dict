package com.example.demo.pojo;

import java.util.Date;

public class Image {
    private int im_id;//图片 ID
    private String im_name;//图片名称
    private String im_url;//存放位置
    private int im_ty_id;//图片类型


    /* getter and setter */
    public int getIm_id(){ return im_id; }
    public void setIm_id(int im_id){
        this.im_id=im_id;
    }

    public String getIm_name(){
        return im_name;
    }
    public void setIm_name(String im_name){
        this.im_name=im_name;
    }

    public String getIm_url(){
        return im_url;
    }
    public void setIm_url(String im_url){
        this.im_url=im_url;
    }

    public int getIm_type(){
        return im_ty_id;
    }
    public void setIm_type(int im_ty_id) {
        this.im_ty_id = im_ty_id;
    }
}
