package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.DailyDataResponse;
import com.example.fitnessapp.model_class.Datum;
import com.example.fitnessapp.model_class.DatumAdapter;
import com.example.fitnessapp.model_class.FoodQuery;
import com.example.fitnessapp.model_class.GetDailyDataResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyDiet extends AppCompatActivity implements View.OnClickListener {
    private EditText searchText, amount;
    private TextView labelDisplay;
    String id = null;
    //    ArrayList<Datum> data = new ArrayList<> (  );
    ArrayList<Datum> data = new ArrayList<> (  );
    private DatumAdapter datumAdapter;
    private RecyclerView datumRecyclerView;
    private TextView calorie_Consumed;
    private TextView calorie_Left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_daily_diet );
        findViewById ( R.id.searchFoodBtn ).setOnClickListener ( this );
//        findViewById ( R.id.addFood ).setOnClickListener ( this );
        searchText = findViewById ( R.id.searchFood );
        labelDisplay = findViewById ( R.id.labelDisplay );
        amount = findViewById ( R.id.amount );
        datumRecyclerView=findViewById ( R.id.datumRecyclerView );
        datumRecyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        calorie_Consumed = findViewById ( R.id.calorieConsumed );
        calorie_Left = findViewById ( R.id.calorieLeft );
    }

    public void requestFood() {

        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( DailyDiet.this ).getUser ().getToken ();
        token = "token " + tempToken;
        final String foodItem = searchText.getText ().toString ().trim ();
        if (foodItem.isEmpty ()) {
            searchText.setError ( "Please enter an item to search" );
            searchText.requestFocus ();
            return;
        }
        Call<FoodQuery> call = RetrofitClient.getInstance ().getApi ().getFoodInfo ( token,foodItem );
        call.enqueue ( new Callback<FoodQuery> () {
            @Override
            public void onResponse(Call<FoodQuery> call, Response<FoodQuery> response) {
                FoodQuery foodQuery = response.body ();
                if (!response.isSuccessful ()) {
                    labelDisplay.setText ( response.code () + "Item Not Found" );
                    return;
                }
                Log.d ( "onResponse: ", String.valueOf ( response.body () ) );
                Log.d ( "onResponse: ", String.valueOf ( response.body ().getId () ) );
                Log.d ( "onResponse: ", String.valueOf ( response ) );
//                int id=0;
                id = String.valueOf ( foodQuery.getId () );
                add ( id );
            }

            @Override
            public void onFailure(Call<FoodQuery> call, Throwable t) {

            }
        } );
    }

    public void add(String id) {


        String idstring = "";
        idstring = String.valueOf ( id );
        labelDisplay.setText ( id );
        int passid = Integer.parseInt ( idstring );
        Toast.makeText ( this, id, Toast.LENGTH_SHORT ).show ();
        String tamount = String.valueOf ( amount.getText () );
//        if(tamount.isEmpty ())
//        {
//            amount.setError ( "Please Enter the amount" );
//            amount.requestFocus ();
//            return;
//        }
        int pamount=1;
        pamount=Integer.parseInt ( tamount );
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( DailyDiet.this ).getUser ().getToken ();
        token = "token " + tempToken;
        Call<DailyDataResponse> call = RetrofitClient.getInstance ().getApi ().postDailyFood ( token,passid,pamount );
        call.enqueue ( new Callback<DailyDataResponse> () {
            @Override
            public void onResponse(Call<DailyDataResponse> call, Response<DailyDataResponse> response) {
                DailyDataResponse dailyDataResponse = response.body ();
                if(dailyDataResponse.getSucess ()) {
                    Log.d ( "hi", "posted suceessfulllyyy" );
                    Toast.makeText ( DailyDiet.this, "Posted sucessfulll yayyy", Toast.LENGTH_SHORT ).show ();
                    getDailyDiet ();
                }
                else
                    Toast.makeText ( DailyDiet.this, ":'(", Toast.LENGTH_SHORT ).show ();
            }

            @Override
            public void onFailure(Call<DailyDataResponse> call, Throwable t) {
                Toast.makeText ( DailyDiet.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );

    }

    public void getDailyDiet(){
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( DailyDiet.this ).getUser ().getToken ();
        token = "token " + tempToken;
        Call<GetDailyDataResponse> call = RetrofitClient.getInstance ().getApi ().getDailyFood ( token );
        call.enqueue ( new Callback<GetDailyDataResponse> () {
            @Override
            public void onResponse(Call<GetDailyDataResponse> call, Response<GetDailyDataResponse> response) {
                String caloriesLeft, caloriesConsumed;
                if(response.body ().getData ().size ()>0){
                    data = new ArrayList<> ( response.body ().getData () );
                    datumAdapter = new DatumAdapter ( DailyDiet.this,data );
                    datumRecyclerView.setAdapter ( datumAdapter );
                    caloriesLeft = String.valueOf (  response.body ().getCaloriesLeft ());
                    caloriesConsumed=String.valueOf ( response.body ().getCaloriesTook () );
                    calorie_Consumed.setText ( caloriesConsumed );
                    calorie_Left.setText ( caloriesLeft );

                }
            }

            @Override
            public void onFailure(Call<GetDailyDataResponse> call, Throwable t) {

            }
        } );

    }

    @Override
    protected void onStart() {
        super.onStart ();
        getDailyDiet ();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFoodBtn:
                requestFood ();
                break;
//            case R.id.addFood:
//                add ( id );
//                break;
        }
    }
}
