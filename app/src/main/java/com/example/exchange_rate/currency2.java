package com.example.exchange_rate;

public class currency2 {
    String F;
    String F_name;
    String T;
    String T_name;
    String exchange;
    String result;
    String updateTime;

    public currency2() {
    }

    public currency2(String f, String f_name, String t, String t_name, String exchange, String result, String updateTime) {
        F = f;
        F_name = f_name;
        T = t;
        T_name = t_name;
        this.exchange = exchange;
        this.result = result;
        this.updateTime = updateTime;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public String getT_name() {
        return T_name;
    }

    public void setT_name(String t_name) {
        T_name = t_name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return
                "货币名称："+F_name+"\n"+"货币名称："+T_name+"\n"+
                        "当前汇率："+result+"\n"+"更新日期："+updateTime+"\n";
    }
}
