package com.example.exchange_rate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class list {
    List<Currency> list=new ArrayList<Currency>();

    public list() {
    }

    public List<Currency> getJsonList(String json) {
        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject paramzObject = rootObject.getJSONObject("result");
            JSONArray feedsArray = paramzObject.getJSONArray("list");
            for (int i = 0; i < feedsArray.length(); i++) {
                JSONObject sonObject = feedsArray.getJSONObject(i);
                String subjectStr = sonObject.getString("name");
                String summaryStr = sonObject.getString("code");
                Currency c=new Currency(subjectStr,summaryStr);
                this.list.add(c);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
