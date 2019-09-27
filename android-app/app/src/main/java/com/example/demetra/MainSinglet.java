package com.example.demetra;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainSinglet {
    
    private static MainSinglet mMainSinglet = null;
    private static String TAG = "MainSinglet";
    private JSONArray mJSONArrayTrk;

    private MainSinglet(){
        
    }
    
    public static MainSinglet get(){
        if(mMainSinglet == null){
            mMainSinglet = new MainSinglet();
        }
        return mMainSinglet;
    }

    //[{"id":5329555120095796224,"name":"Гранд Каньон","restarauntIds":[],"city":"Санкт-Петербург","address":"пр. просвещения 19","clusterId":0,"longitude":0.0,"latitude":0.0},
    // {"id":3076960602461206528,"name":"ТРК Норд","restarauntIds":[7649490832244885504,4397150871713731584],"city":"Санкт-Петербург","address":"пр. просвещения 19","clusterId":0,"longitude":0.0,"latitude":0.0}]
    public void onNewListTrks(String jsonList){
        try {
            JSONObject jsonObject = new JSONObject(jsonList);
            JSONArray jsonArray = jsonObject.getJSONArray("data");//new JSONArray(jsonList);
            Log.i(TAG, " jsonArray.length(); = " +  jsonArray.length());
            mJSONArrayTrk = jsonArray;
        }
        catch (JSONException je){
            je.printStackTrace();
        }
    }
    public String getNameTrk(int position){
        try {
            JSONObject obj = mJSONArrayTrk.getJSONObject(position);
            return obj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "name";
    }

    public String getDistanceTrk(int position) {
        return "10 km";
    }

    public int getCountTrk() {
        if(mJSONArrayTrk == null) return 0;
        return mJSONArrayTrk.length();
    }
}
