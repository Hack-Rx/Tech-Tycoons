package com.example.fitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
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





    public void searchFood() {
        Toast.makeText ( this, "hi", Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFood:
                searchFood ();
                break;
        }
    }

   


}

