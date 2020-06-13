package com.example.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.Food;
import com.example.fitnessapp.model_class.FoodAdapter;
import com.example.fitnessapp.storage.SharedPreferenceManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    ArrayList<Food> foods = new ArrayList<> ();
    private FoodAdapter foodAdapter;
    private RecyclerView food_recyclerView;
    //drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );


        //hooks
        setContentView ( R.layout.activity_dashboard );
        findViewById ( R.id.searchFood ).setOnClickListener ( this );
        food_recyclerView = findViewById ( R.id.food_recyclerView );
        food_recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        drawerLayout = findViewById ( R.id.drawer_layout );
        navigationView = findViewById ( R.id.nav_view );
        toolbar = findViewById ( R.id.toolbar );
//
//        //
        setSupportActionBar ( toolbar );
        navigationView.bringToFront ();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener ( toggle );
        toggle.syncState ();
        navigationView.setNavigationItemSelectedListener ( this );
        navigationView.setCheckedItem ( R.id.nav_home );

        //
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( DashboardActivity.this ).getUser ().getToken ();
        token = "token " + tempToken;
        Call<List<Food>> callFood = RetrofitClient.getInstance ().getApi ().getFood ( token );
        callFood.enqueue ( new Callback<List<Food>> () {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foods = new ArrayList<> ( response.body () );
                foodAdapter = new FoodAdapter ( DashboardActivity.this, foods );
                food_recyclerView.setAdapter ( foodAdapter );
                Toast.makeText ( DashboardActivity.this, "success", Toast.LENGTH_SHORT ).show ();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText ( DashboardActivity.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen ( GravityCompat.START )) {
            drawerLayout.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ();
        }

    }

    @Override
    protected void onStart() {
        super.onStart ();
        if (!SharedPreferenceManager.getInstance ( this ).isSaved ()) {
            Intent intent = new Intent ( this, UserStatistics.class );
            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity ( intent );
        }

    }

    public void searchFood() {
        Intent intent = new Intent ( DashboardActivity.this, SearchFood.class );
        startActivity ( intent );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFood:
                searchFood ();
                break;
        }
    }

    public void logout() {
        SharedPreferenceManager.getInstance ( this ).clear ();
        Intent intent = new Intent ( this, LoginActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity ( intent );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId ()) {
            case R.id.nav_home:
                break;
            case R.id.nav_settings:
                Intent intent = new Intent ( this, EditProfile.class );
                startActivity ( intent );
                break;
            case R.id.nav_profile:
                Intent intent1 = new Intent ( this, UserProfile.class );
                startActivity ( intent1 );
                break;
            case R.id.nav_add:
                Intent intent2 = new Intent ( this, DailyDiet.class );
                startActivity ( intent2 );
                break;
            case R.id.nav_chat:
                Intent intent5 = new Intent ( this, ChatBot.class );
                startActivity ( intent5 );
                break;
            case R.id.nav_Overboard:
                Intent intent6= new Intent ( this, Overboard.class );
                startActivity ( intent6 );
                break;
            case R.id.nav_medical:
                Intent intent4 = new Intent ( this,MedicalProfile.class );
                startActivity ( intent4 );
                break;
            case R.id.nav_logout:
                logout ();
                break;
            case R.id.nav_rewards:
                Intent intent3 = new Intent ( this, Reward.class );
                startActivity ( intent3 );
                break;
        }
        drawerLayout.closeDrawer ( GravityCompat.START );
        return true;
    }
}