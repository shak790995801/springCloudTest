package com.dhcc.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPo {
    private int id;
    private String date;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserPo(int id) {
        this.id = id;
        this.date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    }

    public UserPo() {
    }
}
