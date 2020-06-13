package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.DashboardResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    private TextView name;
    private TextView ageVal;
    private TextView genderVal;
    private TextView heightVal;
    private TextView dietVal;
    private TextView  weightVal;
    private TextView conditionVal;
    private TextView goalVal;
    private TextView bmiVal;
    private TextView bmrVal;
    private TextView calVal;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_profile );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        name = findViewById ( R.id.userName );
        weightVal = findViewById ( R.id.weight );
        conditionVal = findViewById ( R.id.condition );
        goalVal = findViewById ( R.id.goals );
        bmiVal = findViewById ( R.id.bmi );
        bmrVal = findViewById ( R.id.bmr );
        calVal = findViewById ( R.id.calories );
        ageVal = findViewById ( R.id.age );
        genderVal = findViewById ( R.id.gender );
        heightVal = findViewById ( R.id.height );
        dietVal = findViewById ( R.id.diet );


        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( this ).getUser ().getToken ();
        token = "token " + tempToken;


        Call<DashboardResponse> call = RetrofitClient.getInstance ().getApi ().getInfo ( token );
        call.enqueue ( new Callback<DashboardResponse> () {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if (!response.isSuccessful ()) {
                    Toast.makeText ( UserProfile.this, response.code (), Toast.LENGTH_SHORT ).show ();
                }
                DashboardResponse dashboardResponse = response.body ();
                String tname="";
                String weight = "";
                String condition = "";
                String goal = "";
                String bmi = "";
                String bmr = "";
                String cal = "";
                String age = "";
                String gender="";
                String height="";
                String diet="";
                weight += dashboardResponse.getStatistics ().getWeight () ;
                bmr += dashboardResponse.getStatistics ().getBmr () ;
                bmi += dashboardResponse.getStatistics ().getBmi () ;
                goal += dashboardResponse.getStatistics ().getGoals ();
                cal += +dashboardResponse.getStatistics ().getDailyCalories () ;
                condition += dashboardResponse.getStatistics ().getCondition ();
                age+= dashboardResponse.getStatistics ().getAge ();
                diet += dashboardResponse.getStatistics ().getFoodChoice ();
                gender += dashboardResponse.getStatistics ().getGender ();
                height+= dashboardResponse.getStatistics ().getHeight ();
                tname=dashboardResponse.getStatistics ().getName ();
                weightVal.append ( weight );
                conditionVal.append ( condition );
                goalVal.append ( goal );
                bmiVal.append ( bmi );
                bmrVal.append ( bmr );
                calVal.append ( cal );
                ageVal.append ( age );
                genderVal.append ( gender );
                heightVal.append ( height );
                dietVal.append ( diet );
                name.setText ( tname );

            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                Toast.makeText ( UserProfile.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );
    }
}
