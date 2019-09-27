package com.example.demetra;

public class MainSinglet {
    
    private static MainSinglet mMainSinglet = null;
    private MainSinglet(){
        
    }
    
    public static MainSinglet get(){
        if(mMainSinglet == null){
            mMainSinglet = new MainSinglet();
        }
        return mMainSinglet;
    }

    public void onNewListTrks(String s) {
    }

    public String getNameTrk(int position) {
        return "name";
    }

    public String getDistanceTrk(int position) {
        return "10 km";
    }

    public int getCountTrk() {
        return 20;
    }
}
