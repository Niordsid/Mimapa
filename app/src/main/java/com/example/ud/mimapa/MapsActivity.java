package com.example.ud.mimapa;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private final LatLng SAN = new LatLng(12.587151, -81.698713);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void addMarker(View v) {
        mMap.addMarker(new MarkerOptions().position(
                new LatLng(mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude)));
    }
    public void moveCamera(View v) {
        mMap.moveCamera(CameraUpdateFactory. newLatLngZoom (SAN, 15));
    }
    public void camSatelite(View v){
        mMap.setMapType(GoogleMap. MAP_TYPE_SATELLITE );
    }
    public void camNormal(View v){
        mMap.setMapType(GoogleMap. MAP_TYPE_NORMAL );
    }

    public void camTerrain(View v){
        mMap.setMapType(GoogleMap. MAP_TYPE_TERRAIN);
    }

    public void camRoad(View v){
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
    }

    public void camHybrid(View v){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    public void onSearch(View v){
        EditText location_tf = (EditText)findViewById(R.id.address);
        String  location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
              addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Market"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void changeType(View v){

        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap. MAP_TYPE_SATELLITE);
        }
        else{
            mMap.setMapType(GoogleMap. MAP_TYPE_NORMAL);
                }
    }

    public void animateCamera(View v) {
        if (mMap.getMyLocation() != null)
            mMap.animateCamera(CameraUpdateFactory. newLatLngZoom (
                    new LatLng( mMap.getMyLocation().getLatitude(),
                            mMap.getMyLocation().getLongitude()), 15));
    }
    public void mostrarUD(View v) {
        LatLng UD = new LatLng(4.628618, -74.065569);
        Marker miMaker = mMap.addMarker(new MarkerOptions()
                        .position(UD)
                        .title("UD")
                        .snippet("UNIVERSIDAD DISTRITAL")
        );
        miMaker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory. newLatLngZoom (UD, 16));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap. MAP_TYPE_NORMAL );
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnMapClickListener((GoogleMap.OnMapClickListener) this);
    }

    public void onMapClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng).
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }
}
