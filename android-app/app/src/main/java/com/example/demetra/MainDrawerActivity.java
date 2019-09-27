package com.example.demetra;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

public abstract class MainDrawerActivity extends AppCompatActivity {

    protected MainDrawerFragment mMainDrawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setIdContentView());
        FragmentManager fm = getSupportFragmentManager();
        mMainDrawerFragment = (MainDrawerFragment) fm.findFragmentById(idDrawerContainer());
        if(mMainDrawerFragment == null){
            mMainDrawerFragment = new MainDrawerFragment();
            fm.beginTransaction().add(idDrawerContainer(), mMainDrawerFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = DrawerView();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    abstract int idDrawerContainer();
    abstract DrawerLayout DrawerView();
    abstract int setIdContentView();
}
