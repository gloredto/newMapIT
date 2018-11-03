package com.example.eirene.mapit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }

    private void Home(){
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    private void Status(){
        startActivity(new Intent(MainActivity.this, StatusActivity.class));
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
                Status();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
