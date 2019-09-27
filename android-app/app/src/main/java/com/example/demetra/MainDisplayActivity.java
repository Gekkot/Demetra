package com.example.demetra;

import android.Manifest;
import android.content.Context;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

public class MainDisplayActivity extends MainDrawerActivity implements OnMapReadyCallback, LocationListener {


    private GoogleMap mMap;
    private TrkListFragment mTrkListFragment;
    private static final int REQ_LOCATION_PERMISSION = 1;

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
            fragment = new TrkListFragment();
            fm.beginTransaction().add(R.id.list_container, fragment).commit();
        }
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

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(), "onLocationChanged claster: " + getClasterIndex(location.getLatitude(), location.getLongitude()), Toast.LENGTH_LONG).show();
        mTrkListFragment.onUpdateLocation(location.getLatitude(), location.getLongitude());
        if(mMap != null) {
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(location.getLatitude(),  location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(sydney).title("You"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .zoom(12)
                    .bearing(0)
                    .tilt(30)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.animateCamera(cameraUpdate);
        }
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
        Toast.makeText(getApplicationContext(), "onProviderDisabled", Toast.LENGTH_LONG).show();
    }


    @Override
    int idDrawerContainer() {
        return R.id.drawler_continer;
    }

    @Override
    DrawerLayout DrawerView() {
        return findViewById(R.id.drawer_layout);
    }

    @Override
    int setIdContentView() {
        return R.layout.activity_main_display;
    }
}
