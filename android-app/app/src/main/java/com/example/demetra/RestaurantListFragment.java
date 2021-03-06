package com.example.demetra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantListFragment extends Fragment {

    private static final String TAG = "RestaurantListFragment";
    private RecyclerView mRestaurantListRecyclerView;
    private RestaurantAdapter mRestaurantAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.restaurant_list_layout, container, false);
        mRestaurantListRecyclerView = v.findViewById(R.id.recycler_view_restaurant_list);
        mRestaurantListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class RestaurantHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private JSONObject mJSONObject;
        private long mRestarantId;

        public RestaurantHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_restaurants, parent, false));
        }

        public void bind(int position)
        {
            JSONArray jsonArray = MainSinglet.get().getSelectedTrkRestarant();
            String name = "name";
            String urlIcon = null;
            String description = "null";
            if(jsonArray == null) return;

            try {
                mJSONObject = jsonArray.getJSONObject(position);
                name = mJSONObject.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
                ((TextView) this.itemView.findViewById(R.id.restaurant_name)).setText("invalid position");
                return;
            }
            if(mJSONObject != null) {

                try {
                    mRestarantId = mJSONObject.getLong("id");
                } catch (JSONException e) {
                }
                try {
                    urlIcon = mJSONObject.getString("imageUrl");
                } catch (JSONException e) {
                }

                try {
                    description = mJSONObject.getString("description");
                } catch (JSONException e) {
                }
            }

            TextView tv= (TextView) this.itemView.findViewById(R.id.restaurant_name);
            tv.setText(name);
            tv = (TextView) this.itemView.findViewById(R.id.restaurant_description);
            tv.setText(description);
            ImageView iv = this.itemView.findViewById(R.id.icon_restaurant);
            iv.setImageResource(R.mipmap.ic_launcher);
            if(urlIcon != null)
            new DownloadImageTask(iv)
                    .execute(urlIcon);
                    //.execute("https://lh3.googleusercontent.com/1v-Ay1AmsukO2sCByosCdvr3061uG8UKUfpzlPxO8Xi1TPSnVVyBkA90cqiRgxa6kdM=s180");
                    //.execute("https://aliton.ru/img/site-pix/nord-logo-240-120.jpg");
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MainSinglet.get().selectRestaurant(this.getPosition());
            Intent intent = new Intent(getActivity(), MenuActivity.class);
            intent.putExtra(MenuActivity.RESTAURANT_ID_BUNDLE, mRestarantId);
            startActivity(intent);
        }
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder>{
        @NonNull
        @Override
        public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            return new RestaurantHolder(inf, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            JSONArray jsonArray = MainSinglet.get().getSelectedTrkRestarant();
            if(jsonArray == null) return 0;
            return MainSinglet.get().getSelectedTrkRestarant().length();
        }
    }

    private void updateUI() {
        if(isAdded()) {
            Log.i(TAG, "updateUI isAdded");
            mRestaurantAdapter = new RestaurantAdapter();
            mRestaurantListRecyclerView.setAdapter(mRestaurantAdapter);
        }
    }
}
