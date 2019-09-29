package com.example.demetra;

import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestarantListFragment extends Fragment {

    private RecyclerView mRestarantListRecyclerView;
    private RestarantAdapter mRestarantAdapter;

/*    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
        //View v = inflater.inflate(R.layout.solo_recycler_view, container, false);
        //mRestarantListRecyclerView = v.findViewById(R.id.recycler_view);
        //mRestarantListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //updateUI();

        //SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
        //       .findFragmentById(R.id.map_container);
        //mapFragment.getMapAsync(this);
        //return v;
    }*/

    private class RestarantHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public RestarantHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_trk, parent, false));
        }

        public void bind(int position)
        {

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), this.getPosition() + "pos", Toast.LENGTH_SHORT).show();
        }
    }

    private class RestarantAdapter extends RecyclerView.Adapter<RestarantHolder>{
        @NonNull
        @Override
        public RestarantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inf = LayoutInflater.from(getActivity());
            return new RestarantHolder(inf, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RestarantHolder holder, int position) {
            TextView tv= (TextView) holder.itemView.findViewById(R.id.trk_name);
            tv.setText("restatant");
/*            JSONArray jsonArray = MainSinglet.get().getSelectedTrkRestarant();
            JSONObject jsonObject;
            if(jsonArray == null) return;
            TextView tv= (TextView) holder.itemView.findViewById(R.id.trk_name);
            try {
                jsonObject = jsonArray.getJSONObject(position);
                tv.setText(jsonObject.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

*//*            TextView tv= (TextView) holder.itemView.findViewById(R.id.trk_name);
            holder.itemView.setOnClickListener(holder);
            tv.setText(MainSinglet.get().getNameTrk(position));
            tv= (TextView) holder.itemView.findViewById(R.id.trk_distance);
            tv.setText(MainSinglet.get().getDistanceTrk(position));*//*
            holder.bind(position);*/
        }

        @Override
        public int getItemCount() {
            return 20;
//            JSONArray jsonArray = MainSinglet.get().getSelectedTrkRestarant();
//            if(jsonArray == null) return 0;
//            return MainSinglet.get().getSelectedTrkRestarant().length();
        }
    }

    private void updateUI() {
        if(isAdded()) {
            mRestarantAdapter = new RestarantListFragment().mRestarantAdapter;
            mRestarantListRecyclerView.setAdapter(mRestarantAdapter);
        }
    }
}
