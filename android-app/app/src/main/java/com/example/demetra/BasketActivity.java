package com.example.demetra;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
}
