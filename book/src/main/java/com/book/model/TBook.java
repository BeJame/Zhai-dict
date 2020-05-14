package com.book.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer boId;

    private String boName;

    private Integer boNum;

    private String boImgAddr;
}
