package com.example.demetra;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainDisplayActivity extends MainDrawerActivity implements OnMapReadyCallback, LocationListener, TrkListFragment.OnMapButtonListener {


    private GoogleMap mMap;
    private TrkListFragment mTrkListFragment;
    private LatLng mSelectedTrkLatLng;
    private String mSelectedTrkString;
    private static final int REQ_LOCATION_PERMISSION = 1;
    private final String TAG = "MainDisplayActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main_display);
/*        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);*/
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if(mapFragment != null)
            mapFragment.getMapAsync(this);

        Fragment fragment = fm.findFragmentById(R.id.list_container);
        if(fragment == null){
            mTrkListFragment = new TrkListFragment();
            fm.beginTransaction().add(R.id.list_container, mTrkListFragment).commit();
            mTrkListFragment.setOnMapButtonListener(this);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Проверка наличия разрешений
            // Если нет разрешения на использование соответсвующих разркешений выполняем какие-то действия

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION_PERMISSION);
        } else {
            //EnableGPSIfPossible();
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 500, this);
            //manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 500, this);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        removeAllMarkers();
        updateMapMarkers();
        moveCameraOnUser();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQ_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //EnableGPSIfPossible();
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 500, this);
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 500, this);
                }
            } else {
                Toast.makeText(getApplicationContext(), "REQ_LOCATION_PERMISSION fail", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int getClasterIndex(double latitude, double longitude) {
        int intLatitude;
        int intLongitude;
        //latitude -90: южный полюс +90: северный полюс
        //longitude от 0 до 180 :по часовым поясам по гринвичу ( тут рашка)
        //longitude от 0 до -180 :протиы часовым поясам по гринвичу
        //перевод эт все в одну плоскость. чтоб потом было проще вычислять соседей

        //отправной точкой выбираем северный полюс
        if (latitude > 90) latitude = 90;
        else if (latitude < -90) latitude = -90;
        latitude = -latitude;
        latitude += 180;
        //теперь latitude от 0 северного полюса до 180 - южный полюс

        longitude %= 360;
        if (longitude < 0) longitude += 360;
        //теперь longitude строго от 0 до 360

        //теперь каждому кластеру что по вертикали, что по горизонтали отведены 10000 ячеек и всего кластеров 100000000(10000 * 10000)
        latitude = (latitude / 180) * 10000;
        longitude = (longitude / 360) * 10000;

        intLatitude = (int) latitude;
        intLongitude = (int) longitude;

        return intLatitude * 10000 + intLongitude;
    }

    private void removeAllMarkers(){
        if(mMap != null) {
            mMap.clear();
        }
    }
    private void updateMapMarkers(){
        if(mMap != null) {
            LatLng latLng = MainSinglet.get().getMyCurrentLatLng();
            if(latLng != null) {
                MarkerOptions marker = new MarkerOptions().position(latLng).title(this.getResources().getString(R.string.you_there));
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_current_location));
                mMap.addMarker(marker);
            }
            if(mSelectedTrkLatLng != null){
                MarkerOptions marker = new MarkerOptions().position(mSelectedTrkLatLng).title(mSelectedTrkString);
                marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.show_in_map_button));
                mMap.addMarker(marker);
            }
        }
    }

    private void moveCameraOnUser(){
        if(mMap != null) {
            LatLng myLatLng = MainSinglet.get().getMyCurrentLatLng();
            if(myLatLng == null) return;

            if(mSelectedTrkLatLng == null) {

                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                CameraPosition.Builder camPosBuilder = new CameraPosition.Builder()
                        .bearing(0)
                        .tilt(30)
                        .target(myLatLng)
                        .zoom(12);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPosBuilder.build());
                mMap.animateCamera(cameraUpdate);
            }
            else
            {
                LatLngBounds latLngBounds;
                double leftBotLat;
                double leftBotLng;
                double rightTopLat;
                double rightTopLng;
                double latOffset;
                double lngOffset;

                if(myLatLng.latitude < mSelectedTrkLatLng.latitude){
                    leftBotLat = myLatLng.latitude;
                    rightTopLat = mSelectedTrkLatLng.latitude;
                }else {
                    rightTopLat = myLatLng.latitude;
                    leftBotLat = mSelectedTrkLatLng.latitude;
                }

                if(myLatLng.longitude < mSelectedTrkLatLng.longitude){
                    leftBotLng = myLatLng.longitude;
                    rightTopLng = mSelectedTrkLatLng.longitude;
                }else {
                    rightTopLng = myLatLng.longitude;
                    leftBotLng = mSelectedTrkLatLng.longitude;
                }

                latOffset = (rightTopLat - leftBotLat) * 0.2;
                lngOffset = (rightTopLng - leftBotLng) * 0.2;

                rightTopLat += latOffset;
                leftBotLat -= latOffset;
                rightTopLng += lngOffset;
                leftBotLng -= lngOffset;

                //CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(camPosBuilder.build());
                //mMap.animateCamera(cameraUpdate);
                latLngBounds = new LatLngBounds(new LatLng(leftBotLat, leftBotLng), new LatLng(rightTopLat, rightTopLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(), "onLocationChanged claster: " + getClasterIndex(location.getLatitude(), location.getLongitude()), Toast.LENGTH_LONG).show();
        LatLng latLng = new LatLng(location.getLatitude(),  location.getLongitude());
        MainSinglet.get().setMyLatLng(latLng);
        if(mTrkListFragment != null)
            mTrkListFragment.onUpdateLocation(location.getLatitude(), location.getLongitude());
        removeAllMarkers();
        updateMapMarkers();
        moveCameraOnUser();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(getApplicationContext(), "onStatusChanged", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getApplicationContext(), "onProviderEnabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i(TAG, "onProviderDisabled " + s);
        Toast.makeText(getApplicationContext(), "onProviderDisabled "+ s, Toast.LENGTH_LONG).show();
        if(s.toLowerCase().equals("network"))
            EnableGPSIfPossible();
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
        return R.layout.activity_main_display;
    }

    @Override
    ViewGroup getViewGroupForToolbar() {
        return findViewById(R.id.main_linear_layout);
    }


    @Override
    public void OnMapButtonClick(View view, LatLng latLng, String trkName) {
        mSelectedTrkLatLng = latLng;
        mSelectedTrkString = trkName;
        removeAllMarkers();
        updateMapMarkers();
        moveCameraOnUser();
    }

    private void EnableGPSIfPossible()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }
    }

    private  void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.req_loc))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
