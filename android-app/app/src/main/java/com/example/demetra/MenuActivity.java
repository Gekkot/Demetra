package com.example.demetra;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MenuActivity extends MainDrawerActivity {

    public static final String RESTAURANT_ID_BUNDLE = "RESTAURANT_ID_BUNDLE";
    private static final String TAG = "MenuActivity";
    private JSONArray mMenuJSONArray;
    private long mRestaurantId;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Получить идентификатор тревоги. */
        Intent intent = getIntent();
        long restaurantId;
        mRestaurantId = intent.getLongExtra( RESTAURANT_ID_BUNDLE, -1 );
        /* Получить тревогу по ее идентификатору. */
        if(mRestaurantId == -1)
            finish();

        new FetchRestaurantMenu(mRestaurantId).execute();
        mProgressBar = findViewById(R.id.progressBar);
        //updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillRestaurantDescription();
    }

    private void fillRestaurantDescription(){
/*        JSONObject jsonObject = MainSinglet.get().getSelectedRestaurant();
        String name;
        String urlIcon = null;
        if(jsonObject == null) finish();

        Log.i(TAG,jsonObject.toString());

        try {
            name = jsonObject.getString("name");
            //urlIcon = jsonObject.getJSONObject("owner").getString("logoUrl");
        } catch (JSONException e) {
            e.printStackTrace();
            name = "invalid position";
        }


        TextView tv= (TextView) findViewById(R.id.restaurant_name);
        tv.setText(name);
        tv = (TextView) findViewById(R.id.restaurant_description);
        tv.setText("restaurant description restaurant description restaurant description restaurant description restaurant description ");
        ImageView iv = findViewById(R.id.icon_restaurant);
        iv.setImageResource(R.mipmap.ic_launcher);
        if(urlIcon != null) {
            new DownloadImageTask(iv)
                    .execute(urlIcon);
                    //.execute("https://lh3.googleusercontent.com/1v-Ay1AmsukO2sCByosCdvr3061uG8UKUfpzlPxO8Xi1TPSnVVyBkA90cqiRgxa6kdM=s180");
                    //.execute("https://aliton.ru/img/site-pix/nord-logo-240-120.jpg");

        }*/


        String name = "name";
        String urlIcon = null;
        String description = "null";
        JSONObject jsonObject = MainSinglet.get().getSelectedRestaurant();
        if(jsonObject == null) return;

        try {
            name = jsonObject.getString("name");
        } catch (JSONException e) {
        }

        try {
            urlIcon = jsonObject.getString("imageUrl");
        } catch (JSONException e) {
        }

        try {
            description = jsonObject.getString("description");
        } catch (JSONException e) {
        }


        TextView tv= (TextView) findViewById(R.id.restaurant_name);
        tv.setText(name);
        tv = (TextView) findViewById(R.id.restaurant_description);
        tv.setText(description);
        ImageView iv = findViewById(R.id.icon_restaurant);
        iv.setImageResource(R.mipmap.ic_launcher);
        if(urlIcon != null)
            new DownloadImageTask(iv)
                    .execute(urlIcon);
        //.execute("https://lh3.googleusercontent.com/1v-Ay1AmsukO2sCByosCdvr3061uG8UKUfpzlPxO8Xi1TPSnVVyBkA90cqiRgxa6kdM=s180");
        //.execute("https://aliton.ru/img/site-pix/nord-logo-240-120.jpg");
    }

    public class FetchRestaurantMenu extends AsyncTask<Void,Void,String> {

        private long mRestaurantId;

        public FetchRestaurantMenu(long restaurantId){
            mRestaurantId = restaurantId;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String urlStr = "http://"+MainSinglet.SERVER_ADDR+"/menu?restarauntId="+mRestaurantId;
                Log.i(TAG, urlStr);
                String s = Fetchr.getUrlString(urlStr);
                Log.i(TAG, s);
                return s;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "menu json:" + s);
            super.onPostExecute(s);
            if(s == null) {
                return;
            }
            try {
                JSONObject obj = new JSONObject(s);
                if(obj.getString("result").toLowerCase().equals("ok") == false){
                    finish();
                }
                mMenuJSONArray = new JSONObject(s).getJSONArray("data");
            } catch (JSONException e) {
                e.printStackTrace();
                finish();
            }

            updateUI();
        }
    };

    private void updateUI(){
        if(mMenuJSONArray != null && mMenuJSONArray.length() != 0){
            mProgressBar.setVisibility(View.GONE);
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final Context mContext;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            try {
                MenuFragment menuFragment = new MenuFragment(mMenuJSONArray.getJSONObject(position).getJSONArray("menuPositions"));
                return menuFragment;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                return new MenuFragment(new JSONArray("[]"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            try {
                return mMenuJSONArray.getJSONObject(position).getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "null";
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            if(mMenuJSONArray == null) return 0;
            return mMenuJSONArray.length();
        }
    }

    @Override
    int idDrawerContainer() {
        return R.id.drawer_container;
    }

    @Override
    DrawerLayout DrawerView() {
        return findViewById(R.id.drawer_layout);
    }

    @Override
    int setIdContentView() {
        return R.layout.menu_tab_layout;
    }

    @Override
    LinearLayout getViewGroupForToolbar() {
        return findViewById(R.id.main_linear_layout);
    }
}
