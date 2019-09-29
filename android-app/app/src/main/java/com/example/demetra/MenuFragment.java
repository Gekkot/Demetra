package com.example.demetra;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private final String TAG = "MenuFragment";
    private RecyclerView mMenuListRecyclerView;
    private MenuAdapter mMenuAdapter;
    private JSONArray mMenuJSONArray;
    private JSONArray mSpinnerArray;
    private Button mToPayButton;
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

    private class MenuHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        private JSONObject mJSONObject;
        private TextView mCountTextView;
        private Spinner mSpinner;
        public MenuHolder(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_menuposition, parent, false));
        }

        private void showDialog() {

            // DialogFragment.show() will take care of adding the fragment
            // in a transaction.  We also want to remove any currently showing
            // dialog, so make our own transaction and take care of that here.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // Create and show the dialog.
            DialogFragment newFragment = TopingDialog.newInstance(mJSONObject);
            newFragment.show(ft, "dialog");
        }

        private void setSpinner(JSONObject object, int select){

            mSpinner = this.itemView.findViewById(R.id.spinner);
            mSpinner.setVisibility(View.INVISIBLE);
            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            try {
                Log.i(TAG, object.toString());
                mSpinnerArray = object.getJSONArray("foodSizes");
                if(mSpinnerArray.length() != 0){
                    mSpinner.setVisibility(View.VISIBLE);
                }

                for(int i = 0; i < mSpinnerArray.length(); i++){
                    String s = mSpinnerArray.getJSONObject(i).getString("name");
                    Log.i(TAG, s);
                    categories.add(s);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

/*            mSpinner.setVisibility(View.VISIBLE);
            categories.add("Automobile");
            categories.add("Business Services");
            categories.add("Computers");
            categories.add("Education");
            categories.add("Personal");
            categories.add("Travel");*/
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner

            mSpinner.setAdapter(dataAdapter);
            mSpinner.setSelection(select);
            mSpinner.setOnItemSelectedListener(this);
        }


        public void bind(int position)
        {
            String name = "null";
            String desctipter = "null";
            String url = null;
            int count = 0;
            int selectSize = 0;
            int toppingCount = 0;

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
                if(mJSONObject.has("selectSize"))
                    selectSize = mJSONObject.getInt("selectSize");
                toppingCount = mJSONObject.getJSONArray("toppings").length();
                if(mJSONObject.has("imageUrl"))
                    url = mJSONObject.getString("imageUrl");
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
            if(url != null)
                new DownloadImageTask(iv)
                        .execute(url);

            mCountTextView = (TextView) this.itemView.findViewById(R.id.count);
            mCountTextView.setText("" + count);

            tv = (TextView) this.itemView.findViewById(R.id.add_topping_text);
            iv = (ImageView) this.itemView.findViewById(R.id.add_topping_button);
            if(toppingCount > 0) {
                iv.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog();
                    }
                });
            }else{
                iv.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.INVISIBLE);
            }

            setSpinner(mJSONObject, selectSize);


        }

        @Override
        public void onClick(View view) {
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            BasketSinglet.get().onSetMenuPositionSize(mJSONObject, i);
            ((MainDrawerActivity) getActivity()).onUpdateBasket();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

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
