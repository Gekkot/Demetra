package com.example.demetra;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

public class TrkListFragment extends Fragment {
    private RecyclerView mTrkListRecyclerView;
    private trkAdapter mTrkAdapter;
    private GoogleMap mMap;
    private static String TAG = "trkListFragment";

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
                String urlStr = "http://91.218.249.70:4004/city_malls?lat="+latitude+"&long="+longitude;
                urlStr = "http://91.218.249.70:4004/city_malls?lat="+40+"&long="+60;
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
            MainSinglet.get().onNewListTrks(s);
            updateUI();
        }
    };

    private class TrkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TrkHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_trk, parent, false));
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), this.getPosition() + "pos", Toast.LENGTH_SHORT).show();
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
            TextView tv= (TextView) holder.itemView.findViewById(R.id.trk_name);
            holder.itemView.setOnClickListener(holder);
            tv.setText(MainSinglet.get().getNameTrk(position));
            tv= (TextView) holder.itemView.findViewById(R.id.trk_distance);
            tv.setText(MainSinglet.get().getDistanceTrk(position));
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

        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
        //       .findFragmentById(R.id.map_container);
        //mapFragment.getMapAsync(this);
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
}