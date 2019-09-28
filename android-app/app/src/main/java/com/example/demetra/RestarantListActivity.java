package com.example.demetra;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class RestarantListActivity extends MainDrawerActivity {

    private RestaurantListFragment mRestaurantListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
        mRestaurantListFragment = new RestaurantListFragment();
            fm.beginTransaction().add(R.id.fragment_container, mRestaurantListFragment).commit();
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
        return R.layout.drawer_with_fragment_container;
    }

    @Override
    ViewGroup getViewGroupForToolbar() {
        return findViewById(R.id.main_linear_layout);
    }
}
