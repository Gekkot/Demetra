package com.example.demetra;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

public class MainDrawerFragment extends Fragment {
    private static final String TAG = "RestaurantListFragment";
    private RecyclerView mMainDrawertRecyclerView;
    private MainDrawerAdapter mMainDrawerAdapter;

    private final int STOCKS_ITEM = 0;
    private final int RECOMMEND_ITEM = 1;
    private final int NEW_PLACE_ITEM = 2;
    private final int SETTINGS_ITEM = 3;
    private final int HELP_ITEM = 4;
    private final int ABOUT_ITEM = 5;
    private final int ITEM_COUNT = 6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.drawler_layout, container, false);
        mMainDrawertRecyclerView = v.findViewById(R.id.recycler_view_main_drawer);
        mMainDrawertRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class MainDrawerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public MainDrawerHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_drawer, parent, false));
        }

        public void bind(int position)
        {
            TextView tv= (TextView) this.itemView.findViewById(R.id.drawer_item_text);
            ImageView iv = (ImageView) this.itemView.findViewById(R.id.drawer_item_icon);
            switch (position) {
                case STOCKS_ITEM:
                    iv.setImageResource(R.mipmap.ic_stock);
                    tv.setText(R.string.stocks);
                    break;
                case RECOMMEND_ITEM:
                    iv.setImageResource(R.mipmap.ic_recomend);
                    tv.setText(R.string.recommends);
                    break;
                case NEW_PLACE_ITEM:
                    iv.setImageResource(R.mipmap.ic_new_place);
                    tv.setText(R.string.new_places);
                    break;
                case SETTINGS_ITEM:
                    iv.setImageResource(R.mipmap.ic_setting);
                    tv.setText(R.string.settings);
                    break;
                case HELP_ITEM:
                    tv.setText(R.string.help);
                    break;
                case ABOUT_ITEM:
                    tv.setText(R.string.about);
                    break;
            }
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TextView tv= (TextView) this.itemView.findViewById(R.id.drawer_item_text);
            Toast.makeText(getActivity(), tv.getText()+" coming soon", Toast.LENGTH_SHORT).show();
        }
    }

    private class MainDrawerAdapter extends RecyclerView.Adapter<MainDrawerHolder>{
        @NonNull
        @Override
        public MainDrawerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            return new MainDrawerHolder(inf, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MainDrawerHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return ITEM_COUNT;
        }
    }

    private void updateUI() {
        if(isAdded()) {
            Log.i(TAG, "updateUI isAdded");
            mMainDrawerAdapter = new MainDrawerAdapter();
            mMainDrawertRecyclerView.setAdapter(mMainDrawerAdapter);
        }
    }
}