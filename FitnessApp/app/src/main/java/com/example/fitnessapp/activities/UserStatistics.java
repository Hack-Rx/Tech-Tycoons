package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.MultipleChoiceFragment;
import com.example.fitnessapp.model_class.UserStatsResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserStatistics extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private Switch aSwitch;
    Boolean lactoseIntolerance = false;
    private EditText editTextName, editTextWeight,editTextHeight, editTextAge;
    private Spinner genSpinner, goalSpinner, activitySpinner, dietSpinner;
//    private TextView tvSelectedChoicses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature( Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView ( R.layout.activity_user_statistics );
         genSpinner = findViewById(R.id.spinGender);
        genSpinner.setOnItemSelectedListener(this);
         goalSpinner = findViewById(R.id.spinGoals);
        goalSpinner.setOnItemSelectedListener(this);
         activitySpinner = findViewById(R.id.spinActivity);
        activitySpinner.setOnItemSelectedListener(this);
        dietSpinner = findViewById ( R.id.spinDiet );
        dietSpinner.setOnItemSelectedListener ( this );
        editTextName = findViewById ( R.id.nameInput );
        editTextWeight = findViewById ( R.id.weightInput );
        editTextHeight = findViewById ( R.id.heightInput );
        editTextAge = findViewById ( R.id.ageInput );
        aSwitch = findViewById ( R.id.switchlactose );
        findViewById ( R.id.savebtn ).setOnClickListener ( this );
//        tvSelectedChoicses = findViewById ( R.id.tvSelectedChoices );
//        Button selectDisease = findViewById ( R.id.diseaseButton );
//        selectDisease.setOnClickListener ( this );

    }
    @Override
    protected void onStart() {
        super.onStart ();
        if(SharedPreferenceManager.getInstance ( this ).isSaved ()){
            Intent intent = new Intent ( this, MedicalInfo.class );
            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity ( intent );
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void userStats(){

        String tempToken = "";
        String token = "";
        tempToken =  SharedPreferenceManager.getInstance ( UserStatistics.this ).getUser ().getToken ();
        token = "token " + tempToken;
//token = "b41d68a4eab3937d51570837faeefa7c498c4fda";
        String name = editTextName.getText ().toString ().trim ();
        String weightString = editTextWeight.getText ().toString ().trim ();
        String heightString = editTextHeight.getText ().toString ().trim ();
        String ageString = editTextAge.getText ().toString ().trim ();
        int gen =genSpinner.getSelectedItemPosition ();
        int goa = goalSpinner.getSelectedItemPosition ();
        int act = activitySpinner.getSelectedItemPosition ();
        int foodChoice = dietSpinner.getSelectedItemPosition ();
        String gender, goals, activity;
        gender = Integer.toString ( gen );

        goals = Integer.toString ( goa );
        activity = Integer.toString ( act );
        int weight =0;
        int height =0;
        int age=0;

        if (name.isEmpty()) {
            editTextName.setError("What should we call you?");
            editTextName.requestFocus();
            return;
        }
        if (weightString.isEmpty()) {
            editTextWeight.setError("Please enter your weight to calculate BMI");
            editTextWeight.requestFocus();
            return;
        }
        if (heightString.isEmpty()) {
            editTextHeight.setError("Please enter your height to calculate BMI");
            editTextHeight.requestFocus();
            return;
        }
        if(ageString.isEmpty ()){
            editTextAge.setError ( "Please enter your age for relevant suggestions" );
            editTextAge.requestFocus ();
            return;
        }
        if( gen == 0){
            Toast.makeText ( this, "Please select your gender", Toast.LENGTH_LONG ).show ();
        }

        if(goa == 0){
            Toast.makeText ( this, "Please select your goal", Toast.LENGTH_LONG ).show ();
        }

        if( act == 0){
            Toast.makeText ( this, "Please tell us about your daily activity", Toast.LENGTH_LONG ).show ();
        }
        if(foodChoice==0)
        {
            Toast.makeText ( this, "Please tell us about your diet preference", Toast.LENGTH_LONG ).show ();
        }
        if(!"".equals ( weightString ))
        {
            weight = Integer.parseInt ( weightString );
        }
        if(!"".equals ( heightString ))
        {
            height = Integer.parseInt ( heightString );
        }
        if(!"".equals ( ageString ))
        {
            age = Integer.parseInt ( ageString );
        }

        aSwitch.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true);
                {
                    lactoseIntolerance = true;
                }

            }
        } );

        Call<UserStatsResponse>  call = RetrofitClient.getInstance ().getApi ().userInfo ( token,gender,weight,height,goals,activity,age,name, foodChoice,lactoseIntolerance );
        call.enqueue ( new Callback<UserStatsResponse> () {
            @Override
            public void onResponse(Call<UserStatsResponse> call, Response<UserStatsResponse> response) {
                UserStatsResponse userStatsResponse = response.body ();
          Log.d("err", String.valueOf ( response ) );

           if(userStatsResponse!=null) {
             if (userStatsResponse.isSucess ()) {
                SharedPreferenceManager.getInstance (  UserStatistics.this).saveStatistics ( userStatsResponse.getData () );
                 Intent intent = new Intent ( UserStatistics.this, MedicalInfo.class );
                intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                 startActivity ( intent );
                } else {
                     Toast.makeText ( UserStatistics.this, "some error", Toast.LENGTH_SHORT ).show ();
                        }
           }

            }

            @Override
            public void onFailure(Call<UserStatsResponse> call, Throwable t) {
                Toast.makeText ( UserStatistics.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();

            }
        } );


    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.savebtn:
                userStats ();
                break;
//            case R.id.diseaseButton:
//                DialogFragment multiChoiceDialog = new MultipleChoiceFragment ();
//                multiChoiceDialog.setCancelable ( false );
//                multiChoiceDialog.show ( getSupportFragmentManager (),  "MultiChoiceDialog");

        }
    }

//    @Override
//    public void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemList) {
//        StringBuilder stringBuilder = new StringBuilder (  );
//        stringBuilder.append ( "Selected Choices = " );
//        for(String str:selectedItemList){
//            stringBuilder.append ( str+" " );
//            tvSelectedChoicses.setText ( stringBuilder );
//        }
//    }
//
//    @Override
//    public void onNegativeButtonClicked() {
//
//    }
}
