package com.example.sam.testing2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends Fragment {

    private MapView mMapView;
    private GoogleMap mMap;
    //required to use Google Maps Directions API
    private String serverKey = "AIzaSyB_TCjUv_fWR6ATfaA2-vowS-RYm7hEykM";
    //setting up destinations
    private LatLng camera = new LatLng(43.822043, -111.786032);
    private LatLng origin = new LatLng(43.822043, -111.786032);
    private LatLng destination = new LatLng(33.414220, -111.682497);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setting up the fragment
        View rootView = inflater.inflate(R.layout.activity_maps, container, false);

        //for fragments
        mMapView = (MapView) rootView.findViewById(R.id.map1);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        //necessary to get maps working
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 13));
                    requestDirection();
            }
        });

        return rootView;
    }

    //utilizing the akexorcist library
    public void requestDirection() {
        //pass in the server key
        GoogleDirection.withServerKey(serverKey)
                //calling necessary functions
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .execute(new DirectionCallback() {
                    @Override
                    //it was able to get the directions!
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        String status = direction.getStatus();
                        Log.e(status, status);
                        if (direction.isOK()) {
                            mMap.addMarker(new MarkerOptions().position(origin));
                            mMap.addMarker(new MarkerOptions().position(destination));

                            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                            mMap.addPolyline(DirectionConverter.createPolyline(getContext(), directionPositionList, 5, Color.RED));
                        }
                    }

                    @Override
                    //something happened,
                    //we need to throw an error here!
                    public void onDirectionFailure(Throwable t) {
                        // Do something
                    }
                });
    }
}
