package com.example.apple.ljl.model;

/**
 * Created by apple on 2017/12/26.
 */
public class Tb_inaccount {
    private int id;
    private double money;
    private String time;
    private String type;
    private String handler;
    private String mark;
    public Tb_inaccount(){
        super();
    }

    public Tb_inaccount(int id,double money, String type, String time, String handler, String mark) {
        this.id = id;
        this.money = money;
        this.type = type;
        this.time = time;
        this.handler = handler;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
