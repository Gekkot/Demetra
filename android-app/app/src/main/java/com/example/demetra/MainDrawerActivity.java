package com.example.demetra;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

public abstract class MainDrawerActivity extends AppCompatActivity {

    protected MainDrawerFragment mMainDrawerFragment;
    protected ImageView mBasketButton;
    protected TextView mSumBasket;
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

        setToolbar();
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
        mBasketButton = new ImageView(this);

        mBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BasketSinglet.get().isEmpty()) {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.basket_is_empty), Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getBaseContext(), BasketActivity.class);
                startActivity(intent);
            }
        });
        toolbar.addView(mBasketButton);
        mSumBasket = new TextView(getBaseContext());
        mSumBasket.setTextColor(getResources().getColor(R.color.white));
        toolbar.addView(mSumBasket);

        onUpdateBasket();
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

    public void onUpdateBasket(){
        if(BasketSinglet.get().isEmpty())
            mBasketButton.setImageResource(R.mipmap.ic_basket_icon_empty);
        else
            mBasketButton.setImageResource(R.mipmap.ic_basket_icon_notempty);
        double cost = BasketSinglet.get().getCost();
        if(cost == 0.0){
            mSumBasket.setText("");
        }else {
            long c = Math.round(cost);
            mSumBasket.setText(c + "р");
        }
    }

    abstract int idDrawerContainer();
    abstract DrawerLayout DrawerView();
    abstract int setIdContentView();
    abstract ViewGroup getViewGroupForToolbar();
}
