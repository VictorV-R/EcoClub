package com.example.ecoclub.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecoclub.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    final private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    // When clicked on map
                    // Initialize marker options
                    MarkerOptions markerOptions=new MarkerOptions();
                    // Set position of marker
                    markerOptions.position(latLng);
                    // Set title of marker
                    markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                    // Remove all marker
                    googleMap.clear();
                    // Animating to zoom the marker
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    // Add marker on map
                    googleMap.addMarker(markerOptions);
                }
            });
            LatLng sydney = new LatLng(-16.4008114, -71.5346801);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Comunidad 1"));
            LatLng com2 = new LatLng(-16.399305, -71.5360514);
            googleMap.addMarker(new MarkerOptions().position(com2).title("Comunidad 2"));
            LatLng com3 = new LatLng(-16.3990022, -71.5297739);
            googleMap.addMarker(new MarkerOptions().position(com3).title("Comunidad 3"));
            LatLng com4 = new LatLng(-16.3994988, -71.5356846);
            googleMap.addMarker(new MarkerOptions().position(com4).title("Comunidad 4"));
            LatLng com5 = new LatLng(-16.3986127, -71.5343126);
            googleMap.addMarker(new MarkerOptions().position(com5).title("Comunidad 5"));
            LatLng com6 = new LatLng(-16.3974163, -71.5369409);
            googleMap.addMarker(new MarkerOptions().position(com6).title("Comunidad 6"));
            LatLng com7 = new LatLng(-16.399289, -71.5389063);
            googleMap.addMarker(new MarkerOptions().position(com7).title("Comunidad 7"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}