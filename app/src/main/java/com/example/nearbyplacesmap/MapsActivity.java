package com.example.nearbyplacesmap;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.nearbyplacesmap.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat, lng;
    ImageButton atm, bank, hospit, restu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        atm = findViewById(R.id.atm);
        bank = findViewById(R.id.bank);
        hospit = findViewById(R.id.hospital);
        restu = findViewById(R.id.res);



        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=atm");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch []= new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData= new FetchData();
                fetchData.execute(dataFetch);

            }
        });

        hospit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbyplacesmap/json?");
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius=1000");

                stringBuilder.append("&type=hospital");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch []= new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData= new FetchData();
                fetchData.execute(dataFetch);

            }
        });

        restu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius=1000");

                stringBuilder.append("&type=resturants");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch []= new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData= new FetchData();
                fetchData.execute(dataFetch);


            }
        });

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location" + lat + "," + lng);
                stringBuilder.append("&radius=1000");

                stringBuilder.append("&type=bank");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch []= new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData= new FetchData();
                fetchData.execute(dataFetch);

            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(23.889258876393313, 90.31938537894062);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Razu Generel Hospital")
                // below line is use to add custom marker on our map.
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_hospital)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng sydney1 = new LatLng(23.886512132547992, 90.3114031254112);
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Charabag Filaria and General Hospital")
                // below line is use to add custom marker on our map.
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_hospital)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));
         getCurrentLocation();

        LatLng location2 = new LatLng(23.886198215251273, 90.31084522600852);
        mMap.addMarker(new MarkerOptions().position(location2).title("Filaria & General Hospital ফাইলেরিয়া এন্ড জেনারেল হাসপাতাল")
                // below line is use to add custom marker on our map.
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_hospital)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));
        getCurrentLocation();

        LatLng location3 = new LatLng(23.85570984137682, 90.32965308287902);
        mMap.addMarker(new MarkerOptions().position(location3).title("Awasan Medical")
                // below line is use to add custom marker on our map.
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_hospital)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location3));
        getCurrentLocation();
    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void getCurrentLocation (){

        if(ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_code);
            return;

        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        LocationCallback locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                Toast.makeText(getApplicationContext(),"location result is=" + locationResult,
                        Toast.LENGTH_LONG).show();

                if(locationResult == null){

                    Toast.makeText(getApplicationContext(),"Current location is null",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                for (Location location:locationResult.getLocations()){
                    if (location !=null){
                        Toast.makeText(getApplicationContext(),"Current location is=" + location.getLongitude(),
                                Toast.LENGTH_LONG).show();
                    }

                }

            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, null);
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location !=null){

                    lat= location.getLatitude();
                    lng= location.getLongitude();

                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("current location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

            }
        }

        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (Request_code){

            case Request_code:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
        }
    }
}