package com.example.demetra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BasketActivity extends MainDrawerActivity {
    private MenuFragment mMenuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            mMenuFragment = new MenuFragment(BasketSinglet.get().getBasketJSONArray());
            fm.beginTransaction().add(R.id.fragment_container, mMenuFragment).commit();
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
    LinearLayout getViewGroupForToolbar() {
        return findViewById(R.id.main_linear_layout);
    }

    public void onUpdateBasket(){
        double cost = BasketSinglet.get().getCost();
        if(cost == 0.0){
            mSumBasket.setText("");
        }else {
            long c = Math.round(cost);
            mSumBasket.setText(c + "р");
        }
    }

    protected void setToolbar(){
        Toolbar toolbar = new Toolbar(this);
        ImageView button = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 168);
        toolbar.setLayoutParams(layoutParams);
        toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("");
        button.setImageResource(R.drawable.ic_drawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = DrawerView();
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        // Assuming in activity_main, you are using LinearLayout as root
        ViewGroup ll = getViewGroupForToolbar();
        ll.addView(toolbar, 0);
        toolbar.addView(button);
        setSupportActionBar(toolbar);
        mSumBasket = new TextView(getBaseContext());
        mSumBasket.setTextColor(getResources().getColor(R.color.white));
        toolbar.addView(mSumBasket);

        onUpdateBasket();
    }
}
