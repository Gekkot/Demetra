package com.example.demetra;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class TrkListFragment extends Fragment {
    private RecyclerView mTrkListRecyclerView;
    private trkAdapter mTrkAdapter;
    private GoogleMap mMap;
    private static String TAG = "trkListFragment";

    private OnMapButtonListener mOnMapButtonListener;

    /*@Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(59, 30);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }*/

    public class FetchTrkTask extends AsyncTask<Void,Void,String> {

        private double latitude, longitude;

        public FetchTrkTask(double lat, double lon){
            latitude = lat;
            longitude = lon;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                //String urlStr = "http://91.218.249.70:4004/city_malls?lat="+latitude+"&long="+longitude;
                latitude = 60.05; longitude=30.33;
                String urlStr = "http://"+MainSinglet.SERVER_ADDR+"/city_mall?lat="+latitude+"&long="+longitude;
                //urlStr = "http://"+MainSinglet.SERVER_ADDR+"/city_malls?lat="+40+"&long="+60;
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
            super.onPostExecute(s);
            if(s!= null) {
                MainSinglet.get().onNewListTrks(s);
                updateUI();
            }
            else {
                LatLng latLng = MainSinglet.get().getMyCurrentLatLng();
                onUpdateLocation(latLng.latitude, latLng.longitude);
            }
        }
    };

    private class TrkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTrkName;
        TextView mTrkDistance;
        ImageView mOnMapImageView;

        public TrkHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_trk, parent, false));
        }

        public void bind(int position)
        {
            ImageView iv = (ImageView) this.itemView.findViewById(R.id.icon_trk);
            new DownloadImageTask(iv)
                    .execute(MainSinglet.get().getIconTrkUrl(position));
                    //.execute("https://lh3.googleusercontent.com/1v-Ay1AmsukO2sCByosCdvr3061uG8UKUfpzlPxO8Xi1TPSnVVyBkA90cqiRgxa6kdM=s180");
            mTrkName = this.itemView.findViewById(R.id.trk_name);
            mTrkName.setText(MainSinglet.get().getNameTrk(position));
            mTrkDistance =  this.itemView.findViewById(R.id.trk_distance);
            mTrkDistance.setText(MainSinglet.get().getDistanceTrk(position));
            mOnMapImageView = this.itemView.findViewById(R.id.image_on_map);
            mOnMapImageView.setImageResource(R.mipmap.show_in_map_button);
            this.itemView.setOnClickListener(this);
            mOnMapImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.equals(mOnMapImageView)) {
                //Toast.makeText(getActivity(), this.getPosition() + "button", Toast.LENGTH_SHORT).show();
                if(mOnMapButtonListener != null) {
                    mOnMapButtonListener.OnMapButtonClick(view, MainSinglet.get().getLatLngTrk(this.getPosition()), mTrkName.getText().toString());
                }
            }
            else {
                Toast.makeText(getActivity(), this.getPosition() + "pos", Toast.LENGTH_SHORT).show();
                MainSinglet.get().selectTrk(this.getPosition());
                Intent intent = new Intent(getActivity(), RestaurantListActivity.class);
                startActivity(intent);
            }
        }
    }

    private class trkAdapter extends RecyclerView.Adapter<TrkHolder>{
        @NonNull
        @Override
        public TrkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            return new TrkHolder(inf, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TrkHolder holder, int position) {
/*            TextView tv= (TextView) holder.itemView.findViewById(R.id.trk_name);
            holder.itemView.setOnClickListener(holder);
            tv.setText(MainSinglet.get().getNameTrk(position));
            tv= (TextView) holder.itemView.findViewById(R.id.trk_distance);
            tv.setText(MainSinglet.get().getDistanceTrk(position));*/
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return MainSinglet.get().getCountTrk();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trk_list_layout, container, false);
        mTrkListRecyclerView = v.findViewById(R.id.RecyclerViewTrkList);
        mTrkListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    private void updateUI() {
        if(isAdded()) {
            mTrkAdapter = new trkAdapter();
            mTrkListRecyclerView.setAdapter(mTrkAdapter);
        }
    }

    public void onUpdateLocation(double lat, double lon){
        new FetchTrkTask(lat, lon).execute();
    }

    interface OnMapButtonListener{
        void OnMapButtonClick(View view, LatLng latLng, String trkName);
    }

    public void setOnMapButtonListener(OnMapButtonListener listener){
        mOnMapButtonListener = listener;
    }
}