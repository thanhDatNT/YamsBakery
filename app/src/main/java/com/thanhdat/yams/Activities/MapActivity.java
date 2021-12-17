package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.thanhdat.yams.R;

import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private static GoogleMap mMap;
    private boolean locationPermissionGranted;
    private Toolbar toolbar;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    private static String[] likelyPlaceNames;
    private static String[] likelyPlaceAddresses;
    private static List[] likelyPlaceAttributions;
    private static LatLng[] likelyPlaceLatLngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

        linkViews();

        getLocationPermission();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(R.anim.translate_slide_enter, R.anim.translate_slide_exit);
        });
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {
        if (locationPermissionGranted) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Task<Location> locationResult = client.getLastLocation();
            locationResult.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                mMap = googleMap;
                                LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(myLocation).title("I am here!"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 13));
                                showCurrentPlace();
                            }
                        });

                    }
                }
            });
        }
    }

    private void showCurrentPlace() {
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);

        @SuppressWarnings("MissingPermission") final
        Task<FindCurrentPlaceResponse> placeResult = Places.createClient(this).findCurrentPlace(request);
        placeResult.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
            @Override
            public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                if (task.isSuccessful()) {
                    FindCurrentPlaceResponse likelyPlaces = task.getResult();

                    // Set the count, handling cases where less than 5 entries are returned.
                    int count;
                    if (likelyPlaces.getPlaceLikelihoods().size() < 5) {
                        count = likelyPlaces.getPlaceLikelihoods().size();
                    } else {
                        count = 5;
                    }

                    int i = 0;
                    likelyPlaceNames = new String[count];
                    likelyPlaceAddresses = new String[count];
                    likelyPlaceAttributions = new List[count];
                    likelyPlaceLatLngs = new LatLng[count];

                    for (PlaceLikelihood placeLikelihood : likelyPlaces.getPlaceLikelihoods()) {
                        // Build a list of likely places to show the user.
                        likelyPlaceNames[i] = placeLikelihood.getPlace().getName();
                        likelyPlaceAddresses[i] = placeLikelihood.getPlace().getAddress();
                        likelyPlaceAttributions[i] = placeLikelihood.getPlace()
                                .getAttributions();
                        likelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                        i++;
                        if (i > (count - 1)) {
                            break;
                        }
                    }
                    openPlacesDialog();
                }
                else {
                    Log.e("Map Activity:", "Exception: %s", task.getException());
//                    Location Time out
                }
            }
        });
    }

    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = likelyPlaceLatLngs[which];
                String markerSnippet = likelyPlaceAddresses[which];
                if (likelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + likelyPlaceAttributions[which];
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mMap.addMarker(new MarkerOptions().title(likelyPlaceNames[which]).position(markerLatLng).snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng, 10));
            }
        };
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("").setItems(likelyPlaceNames, listener);
        dialog.create().show();
    }

    private void linkViews() {
        toolbar= findViewById(R.id.toolbarMap);
    }

}