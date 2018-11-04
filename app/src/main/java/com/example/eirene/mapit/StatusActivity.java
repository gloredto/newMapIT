package com.example.eirene.mapit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {


    static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    LocationManager locationManager;

    private EditText userStatus;
    private Button userstatusShare;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setupUIViews();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getLocation();


        userstatusShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                getLocation();





                finish();
            }
        });

    }

    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location != null) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                String status = userStatus.getText().toString();
                //myRef.child("userStatus").setValue(status);
                double userLatitude = location.getLatitude();
                double userLongitude = location.getLongitude();
                UserProfile userProfile  = new UserProfile(userLatitude,userLongitude,status);
                myRef.child("Status").setValue(userProfile);
                //myRef.child("userLatitude").setValue(userProfile.userLatitude);
                //myRef.child("userLongitute").setValue(userProfile.userLongitude);


            }
        }
    }

    private void setupUIViews(){
        userStatus = findViewById(R.id.mitStatus);
        userstatusShare = findViewById(R.id.btnShare);
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StatusActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(StatusActivity.this, AboutActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(StatusActivity.this, ProfileActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(StatusActivity.this, MapsActivity.class));
    }

    private void Home(){
        startActivity(new Intent(StatusActivity.this, MainActivity.class));
    }

    private void Statusmenu(){
        startActivity(new Intent(StatusActivity.this, StatusActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
            break;
            case  R.id.homeMenu:{
                Home();
            }
            break;
            case R.id.aboutMenu:{
                About();
            }
            break;
            case R.id.profileMenu:{
                Profile();
            }
            break;
            case R.id.mapsMenu:{
                Maps();
            }
            break;
            case R.id.statusMenu:{
                Statusmenu();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
