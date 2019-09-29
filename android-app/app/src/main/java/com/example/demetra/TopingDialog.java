package com.example.demetra;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopingDialog extends DialogFragment {
        JSONObject mMenuPositionJSONObject;
        private final String TAG = "TopingDialog";
        private MenuAdapter mMenuAdapter;
        private RecyclerView mMenuRecyclerView;


        /**
         * Create a new instance of MyDialogFragment, providing "num"
         * as an argument.
         */
        static TopingDialog newInstance(JSONObject menuObj) {
            TopingDialog f = new TopingDialog();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putString("menuObj", menuObj.toString());
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String s = getArguments().getString("menuObj");
            try {
                mMenuPositionJSONObject = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.solo_recycler_view, container, false);

            mMenuRecyclerView = v.findViewById(R.id.recycler_view);
            mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            updateUI();
            return v;
        }

    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCounter;
        private int counter;
        public MenuHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_toping, parent, false));
        }

        public void bind(int position){
            JSONArray jsonArray;
            JSONObject jsonObject;
            String name = "null";
            String price = "null";
            int count = 0;
            try {
                jsonArray = mMenuPositionJSONObject.getJSONArray("toppings");
                jsonObject = jsonArray.getJSONObject(position);
                name = jsonObject.getString("name");
                price = "" + (int) jsonObject.getDouble("price");
                if(jsonObject.has("count"))
                    count = jsonObject.getInt("count");
            } catch (JSONException e) {
            }
            counter = count;

            TextView tv = this.itemView.findViewById(R.id.topping_name);
            tv.setText(name);
            tv = this.itemView.findViewById(R.id.topping_price);
            tv.setText(price);

            mCounter = this.itemView.findViewById(R.id.count);
            mCounter.setText("" + counter);

            ImageView iv = this.itemView.findViewById(R.id.minus_count);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(counter == 0) return;
                    counter--;
                    mCounter.setText("" + counter);
                }
            });
            iv = this.itemView.findViewById(R.id.plus_count);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter++;
                    mCounter.setText("" + counter);
                }
            });

        }

        @Override
        public void onClick(View view) {
        }

    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder>{
        @NonNull
        @Override
        public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            return new MenuHolder(inf, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            JSONArray jsonArray;
            try {
                jsonArray = mMenuPositionJSONObject.getJSONArray("toppings");
                return jsonArray.length();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return 0;
        }
    }

    private void updateUI() {
        if(isAdded()) {
            Log.i(TAG, "updateUI isAdded");
            mMenuAdapter = new MenuAdapter();
            mMenuRecyclerView.setAdapter(mMenuAdapter);
        }
    }






    }

