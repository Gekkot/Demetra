package com.example.demetra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuFragment extends Fragment {

    private final String TAG = "MenuFragment";
    private RecyclerView mMenuListRecyclerView;
    private MenuAdapter mMenuAdapter;
    JSONArray mMenuJSONArray;
    Button mToPayButton;
    public MenuFragment(JSONArray menu){
        super();
        mMenuJSONArray = menu;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.basket_layout, container, false);
        //View v = inflater.inflate(R.layout.tab_host_eample, container, false);
        mMenuListRecyclerView = v.findViewById(R.id.recycler_view);
        mMenuListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mToPayButton =v.findViewById(R.id.to_pay_button);

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private JSONObject mJSONObject;
        private TextView mCountTextView;
        public MenuHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_menuposition, parent, false));
        }

        public void bind(int position)
        {
            String name = "null";
            String desctipter = "null";
            int count = 0;

            try {
                JSONObject inBasket;
                mJSONObject = mMenuJSONArray.getJSONObject(position);
                inBasket = BasketSinglet.get().findJSONObjectById(mJSONObject.getLong("id"));
                if(inBasket != null) {
                    mJSONObject = inBasket;
                    count = mJSONObject.getInt("count");
                }
                name = mJSONObject.getString("name");
                desctipter = mJSONObject.getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TextView tv= (TextView) this.itemView.findViewById(R.id.menu_position_name);
            tv.setText(name);
            tv= (TextView) this.itemView.findViewById(R.id.menu_position_descriptor);
            tv.setText(desctipter);
            ImageView iv = (ImageView) this.itemView.findViewById(R.id.minus_count);
            iv.setImageResource(R.mipmap.ic_minus_position_button);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(mCountTextView.getText().toString());
                    if(count != 0) {
                        count--;
                        mCountTextView.setText("" + count);
                        BasketSinglet.get().onChangeCountMenuposition(mJSONObject, count);
                        ((MainDrawerActivity) getActivity()).onUpdateBasket();
                        setVisibilityToPayButton();
                    }
                }
            });
            iv = (ImageView) this.itemView.findViewById(R.id.plus_count);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(mCountTextView.getText().toString());
                    count ++;
                    mCountTextView.setText(""+count);
                    BasketSinglet.get().onChangeCountMenuposition(mJSONObject, count);
                    ((MainDrawerActivity) getActivity()).onUpdateBasket();
                    setVisibilityToPayButton();
                }
            });

            iv.setImageResource(R.mipmap.ic_plus_position_button);
            iv = (ImageView) this.itemView.findViewById(R.id.icon_menu_position);
            iv.setImageResource(R.mipmap.ic_launcher);
            mCountTextView = (TextView) this.itemView.findViewById(R.id.count);
            mCountTextView.setText("" + count);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), this.getPosition() + "pos", Toast.LENGTH_SHORT).show();
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
            return mMenuJSONArray.length();
        }
    }

    private void updateUI() {
        if(isAdded()) {
            Log.i(TAG, "updateUI isAdded");
            mMenuAdapter = new MenuAdapter();
            mMenuListRecyclerView.setAdapter(mMenuAdapter);
            setVisibilityToPayButton();
        }
    }
    private void setVisibilityToPayButton(){
        if(BasketSinglet.get().isEmpty()){
            mToPayButton.setVisibility(View.INVISIBLE);
        }else{
            mToPayButton.setVisibility(View.VISIBLE);
        }
    }
}
