package com.example.exchange_rate;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class list {


    public list() {
    }

    public List<Currency> getJsonList(String json) {
        List<Currency> list=new ArrayList<Currency>();
        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject paramzObject = rootObject.getJSONObject("result");
            JSONArray feedsArray = paramzObject.getJSONArray("list");
            for (int i = 0; i < feedsArray.length(); i++) {
                JSONObject sonObject = feedsArray.getJSONObject(i);
                String subjectStr = sonObject.getString("name");
                String summaryStr = sonObject.getString("code");
                Currency c=new Currency(subjectStr,summaryStr);
                list.add(c);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<currency2> getJsonList2(String json) {
        List<currency2> list=new ArrayList<currency2>();
        if(!json.equals("错误")){
            try {
                JSONObject rootObject = new JSONObject(json);
                JSONArray paramzObject = rootObject.getJSONArray("result");
                for (int i = 0; i < paramzObject.length(); i++) {
                    JSONObject sonObject = paramzObject.getJSONObject(i);
                    String F = sonObject.getString("currencyF");
                    String F_Name = sonObject.getString("currencyF_Name");
                    String T = sonObject.getString("currencyT");
                    String T_Name = sonObject.getString("currencyT_Name");
                    String exchange = sonObject.getString("exchange");
                    String result = sonObject.getString("result");
                    String updateTime = sonObject.getString("updateTime");
                    currency2 c=new currency2(F,F_Name,T,T_Name,exchange,result,updateTime);
                    list.add(c);
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
