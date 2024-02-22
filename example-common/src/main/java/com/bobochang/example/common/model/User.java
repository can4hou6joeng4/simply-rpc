package com.bobochang.example.common.model;

import java.io.Serializable;

/**
 * @author bobochang
 * @Description 用户实体类
 * @Date 2024/2/21 - 09:58
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
