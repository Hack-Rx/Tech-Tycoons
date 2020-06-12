package com.example.fitnessapp.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.FoodModel;
import com.example.fitnessapp.model_class.FoodQuery;
import com.example.fitnessapp.model_class.QueryAdapter;
import com.example.fitnessapp.storage.SharedPreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFood extends AppCompatActivity implements View.OnClickListener{
    private TextView labelDisplay;
    RecyclerView recyclerView;
    ArrayList<FoodModel> foodModels;
    QueryAdapter queryAdapter;
    private TextView foodname;
    private ImageView foodImage;
    private EditText searchText;
    private TextView probs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar ().hide (); // hide the title bar
        setContentView ( R.layout.activity_search_food );
        searchText = findViewById ( R.id.searchFood );
        labelDisplay=findViewById ( R.id.labelDisplay );
        findViewById ( R.id.searchFoodBtn ).setOnClickListener ( this );
        recyclerView = findViewById ( R.id.recycler_view );
        foodname = findViewById ( R.id.food_name );
        foodImage = findViewById ( R.id.food_image );
        probs = findViewById ( R.id.probs );
    }


    public void requestFood(){

        final String foodItem = searchText.getText ().toString ().trim ();
        if(foodItem.isEmpty ()){
            searchText.setError ( "Please enter an item to search" );
            searchText.requestFocus ();
            return;
        }


        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( SearchFood.this ).getUser ().getToken ();
        token = "token " + tempToken;
//
        Call<FoodQuery> call = RetrofitClient.getInstance ().getApi ().getFoodInfo (token,foodItem  );
        Toast.makeText ( this, "gm", Toast.LENGTH_SHORT ).show ();

        call.enqueue ( new Callback<FoodQuery> () {
            @Override
            public void onResponse(Call<FoodQuery> call, Response<FoodQuery> response) {
                if(!response.isSuccessful ()){
                    labelDisplay.setText ( response.code () +"Item Not Found" );
                    return;
                }
                String name="", calorie="", fat="",protein="", carbohydrate="", image="";
               String problems="",tcategory="", tfoodGroup="", tavailability="";
               String tlactose="";
                FoodQuery foodQuery = response.body ();
                name=foodQuery.getName ();

                calorie = String.valueOf (  foodQuery.getCalories ()) ;
                fat = foodQuery.getFat ();
                protein= foodQuery.getProtein ();
                carbohydrate= foodQuery.getCarbohydrate();
                image = foodQuery.getImage ();
                tcategory += foodQuery.getCategory ();
                tfoodGroup +=  foodQuery.getFoodGroup ();
                tlactose += String.valueOf ( foodQuery.getLactoseIntolerance () );
                tavailability = "";
                foodname.setText ( name );
                Picasso.get ().load ( image).into ( foodImage );


                for(int i =0;i<foodQuery.getProblemsCanSolve ().size ();i++){
                    problems += foodQuery.getProblemsCanSolve ().get ( i ).getName ()+"\n";
                }
                for(int i=0;i<foodQuery.getAvailablity ().size ();i++){
                    tavailability += foodQuery.getAvailablity ().get ( i ).getName ()+"\t";
                }


                Integer[] imageicon = {R.drawable.dietform, R.drawable.foodgroup, R.drawable.lactoseintolerance, R.drawable.availabiluity, R.drawable.problems, R.drawable.calories, R.drawable.fats, R.drawable.protein, R.drawable.carbohydrate};
                String[] label = {"Category","Food-Group","Lactose Content","Availability","Helpful in","Calories", "Fats", "Proteins", "Carbohydrates"};
                String[] values = {tcategory,tfoodGroup,tlactose,tavailability,problems,calorie, fat, protein, carbohydrate};

                foodModels=new ArrayList<> (  );
                for(int i=0;i<label.length;i++){
                    FoodModel model = new FoodModel (  imageicon[i], label[i],values[i] );
                    foodModels.add ( model );
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager (
                        SearchFood.this,LinearLayoutManager.HORIZONTAL,false
                );
                recyclerView.setLayoutManager ( layoutManager );
                recyclerView.setItemAnimator (new DefaultItemAnimator () );

                queryAdapter = new QueryAdapter ( foodModels, SearchFood.this );
                recyclerView.setAdapter ( queryAdapter );
            }
            @Override
            public void onFailure(Call<FoodQuery> call, Throwable t) {
labelDisplay.setText ( t.getMessage ( ));
            }
        } );


    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFoodBtn:
                requestFood ();
                break;
        }
    }
}
