package com.cloudwearing.jim.entity;

import java.util.regex.Pattern;

public class QQ {

    private String qq;

    public String getQq() {
        return qq;
    }

    @Override
    public String toString() {
        return qq;
    }

    public QQ(String qq) {
        if (Pattern.matches("\\d+", qq)) this.qq = qq;
        else throw new IllegalArgumentException("！！！错误的QQ号：" + qq);
    }
}
