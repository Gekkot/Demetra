package com.example.demetra;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BasketSinglet {
    private static BasketSinglet self = null;
    private JSONArray mBasketJSONArray;

    private BasketSinglet(){
        try {
            mBasketJSONArray = new JSONArray("[]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static BasketSinglet get(){
        if(self == null)
            self = new BasketSinglet();
        return self;
    }

    public void onChangeCountMenuposition(JSONObject menuPos, int count){
        //ищем запись с таким id
        JSONObject jsonObject;
        long id;
        try {
            id = menuPos.getLong("id");
            for (int position = 0; position < mBasketJSONArray.length(); position++) {
                jsonObject = mBasketJSONArray.getJSONObject(position);
                if(jsonObject.getLong("id") == id){
                    if(count == 0){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            mBasketJSONArray.remove(position);
                        }else {
                            jsonObject.put("count", count);
                        }
                    }else{
                        jsonObject.put("count", count);
                    }

                    return;
                }

            }
            menuPos.put("count", count);
            mBasketJSONArray.put(menuPos);
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    public JSONObject findJSONObjectById(long id){
        JSONObject jsonObject;
        try {
            for (int position = 0; position < mBasketJSONArray.length(); position++) {
                jsonObject = mBasketJSONArray.getJSONObject(position);
                if(jsonObject.getLong("id") == id){
                    return jsonObject;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmpty(){
        boolean ret = true;
        if(mBasketJSONArray.length() == 0)
            return true;
        JSONObject jsonObject;
        for (int position = 0; position < mBasketJSONArray.length(); position++) {

            try {
                jsonObject = mBasketJSONArray.getJSONObject(position);
                if(jsonObject.getInt("count") != 0){
                    ret = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return ret;
    }

    public JSONArray getBasketJSONArray(){
        return mBasketJSONArray;
    }

    public double getCost(){
        double cost = 0;
        JSONObject jsonObject;
        for (int position = 0; position < mBasketJSONArray.length(); position++) {

            try {
                jsonObject = mBasketJSONArray.getJSONObject(position);
                cost += jsonObject.getDouble("price") * jsonObject.getInt("count");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return cost;
    }
}
