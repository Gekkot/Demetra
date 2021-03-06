package com.example.demetra;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainSinglet {
    
    private static MainSinglet mMainSinglet = null;
    private static String TAG = "MainSinglet";
    private JSONArray mJSONArrayTrk;
    private LatLng mCurrentLatLon;
    private JSONArray mJSONArraySelectedTrkRestarant;
    private JSONObject mSelectedRestaurantJSONObject;
    public static final String SERVER_ADDR = "172.20.42.77:4004";

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
        return "null";
    }

    public String getIconTrkUrl(int position)
    {
        try {
            JSONObject obj = mJSONArrayTrk.getJSONObject(position);
            return obj.getString("imageUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public String getDistanceTrk(int position) {
        return "10 km";
    }

    public int getCountTrk() {
        if(mJSONArrayTrk == null) return 0;
        return mJSONArrayTrk.length();
    }

    public LatLng getLatLngTrk(int position){
        LatLng ret = new LatLng(0.0, 0.0);
        try {
            JSONObject obj = mJSONArrayTrk.getJSONObject(position);
            return new LatLng(obj.getDouble("latitude"), obj.getDouble("longitude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new LatLng(0.0, 0.0);
    }

    public void setMyLatLng(LatLng currentPos){
        mCurrentLatLon = currentPos;
    }
    public LatLng getMyCurrentLatLng(){
        return mCurrentLatLon;
    }

    public void selectTrk(int position){
        try {
            JSONObject obj = mJSONArrayTrk.getJSONObject(position);
            mJSONArraySelectedTrkRestarant = obj.getJSONArray("restarauntList");
            //mJSONArraySelectedTrkRestarant = new JSONArray("[{\"id\":1,\"name\":\"РћРєРѕР»РёС†Р°\",\"city\":\"РЎР°РЅРєС‚-РџРµС‚РµСЂР±СѓСЂРі\",\"address\":\"РїСЂ. РїСЂРѕСЃРІРµС‰РµРЅРёСЏ 19\",\"latitude\":0,\"longitude\":0,\"cityMollID\":1029038014413654016,\"ownerID\":3},{\"id\":2,\"name\":\"KFC\",\"city\":\"РЎР°РЅРєС‚-РџРµС‚РµСЂР±СѓСЂРі\",\"address\":\"РїСЂ. РїСЂРѕСЃРІРµС‰РµРЅРёСЏ 19\",\"latitude\":0,\"longitude\":0,\"cityMollID\":1029038014413654016,\"ownerID\":2}]");//"{"id":3,"name":"KFC","city":"РЎР°РЅРєС‚-РџРµС‚РµСЂР±СѓСЂРі","address":"РїСЂРѕСЃРї. Р­РЅРіРµР»СЊСЃР°, 154","latitude":0,"longitude":0,"cityMollID":7676728332671206400,"ownerID":2},{"id":4,"name":"BlackStar Burger","city":"РЎР°РЅРєС‚-РџРµС‚РµСЂР±СѓСЂРі","address":"Р›РёРіРѕРІСЃРєРёР№ РїСЂРѕСЃРї., 30","latitude":0,"longitude":0,"cityMollID":7676728332671206400,"ownerID":4}]
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getSelectedTrkRestarant(){
        return mJSONArraySelectedTrkRestarant;
    }

    public void selectRestaurant(int position){
        try {
            mSelectedRestaurantJSONObject = mJSONArraySelectedTrkRestarant.getJSONObject(position);
      } catch (JSONException e) {
            e.printStackTrace();
            mSelectedRestaurantJSONObject = null;
        }
    }

    public JSONObject getSelectedRestaurant(){
        return mSelectedRestaurantJSONObject;
    }
}
