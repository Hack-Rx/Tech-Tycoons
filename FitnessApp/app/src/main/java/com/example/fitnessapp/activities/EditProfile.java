package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.DashboardResponse;
import com.example.fitnessapp.model_class.EditProfileResponse;
import com.example.fitnessapp.model_class.UserStatsResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    private Switch aSwitch;
    Boolean lactoseIntolerance = false;
    private TextInputEditText name, nameVal;
    private TextInputEditText ageVal,agecall;
    private TextInputEditText weightVal,weightcall;
    private TextInputEditText heightVal,heightcall;
    private Spinner genSpinner, goalSpinner, activitySpinner, dietSpinner;
    private Spinner genSpinnercall, goalSpinnercall, activitySpinnercall, dietSpinnercall;
    private TextView uname;
    private TextView nameabove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_edit_profile );
        findViewById ( R.id.updatebtn ).setOnClickListener ( this );
        weightVal = findViewById ( R.id.spweight );
        heightVal = findViewById ( R.id.sphright );
        ageVal = findViewById ( R.id.spage );
        name = findViewById ( R.id.spname );
        uname = findViewById ( R.id.uname );
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( this ).getUser ().getToken ();
        token = "token " + tempToken;
        genSpinner = findViewById ( R.id.spinGender );
        goalSpinner = findViewById ( R.id.spinGoals );
        activitySpinner = findViewById ( R.id.spinActivity );
        dietSpinner = findViewById ( R.id.spinDiet );
        name.setText ( SharedPreferenceManager.getInstance ( this ).getUser ().getUsername () );
        aSwitch = findViewById ( R.id.switchlactose );
        nameabove = findViewById ( R.id.full_name );


        Call<DashboardResponse> call = RetrofitClient.getInstance ().getApi ().getInfo ( token );
        call.enqueue ( new Callback<DashboardResponse> () {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if (!response.isSuccessful ()) {
                    Toast.makeText ( EditProfile.this, response.code (), Toast.LENGTH_SHORT ).show ();
                }
                DashboardResponse dashboardResponse = response.body ();
                String fullname="";
                String weight = "";
                String username="";
                String activity = "";
                String goal = "";
                String gender = "";
                String age = "";
                String height = "";
                String diet="";
                Boolean lactoseIntolerance;
                weight += dashboardResponse.getStatistics ().getWeight ();
                goal += dashboardResponse.getStatistics ().getGoals ();
                age += dashboardResponse.getStatistics ().getAge ();
                gender += dashboardResponse.getStatistics ().getGender ();
                height += dashboardResponse.getStatistics ().getHeight ();
                activity += dashboardResponse.getStatistics ().getActivity ();
                diet += dashboardResponse.getStatistics ().getFoodChoice ();
                lactoseIntolerance = dashboardResponse.getStatistics ().getLactoseIntolerance ();
                username+=dashboardResponse.getStatistics ().getUsername ();
                fullname = dashboardResponse.getStatistics ().getName ();
                uname.append ( username );
                weightVal.append ( weight );
                ageVal.append ( age );
                heightVal.append ( height );
                nameabove.setText ( fullname );
                for (int i = 1; i <= genSpinner.getCount (); i++) {
                    if (genSpinner.getItemAtPosition ( i ).equals ( gender )) {
                        genSpinner.setSelection ( i );
                        break;
                    }
                }
                for (int i = 1; i < goalSpinner.getCount (); i++) {
                    if (goalSpinner.getItemAtPosition ( i ).equals ( goal )) {
                        goalSpinner.setSelection ( i );
                        break;
                    }
                }
                for (int i = 1; i <activitySpinner.getCount (); i++) {
                    if (activitySpinner.getItemAtPosition ( i ).equals ( activity )) {
                        activitySpinner.setSelection ( i );
                        break;
                    }
                }
                for (int i = 1; i <dietSpinner.getCount (); i++) {
                    if (dietSpinner.getItemAtPosition ( i ).equals ( diet )) {
                        dietSpinner.setSelection ( i );
                        break;
                    }
                }
                if(lactoseIntolerance){
                    aSwitch.setChecked ( true );
                }

            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                Toast.makeText ( EditProfile.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );

    }


    public void hello(){

        weightcall = findViewById ( R.id.spweight );
        heightcall = findViewById ( R.id.sphright );
        agecall = findViewById ( R.id.spage );
        nameVal= findViewById ( R.id.spname );
        genSpinnercall = findViewById ( R.id.spinGender );
        goalSpinnercall = findViewById ( R.id.spinGoals );
        activitySpinnercall = findViewById ( R.id.spinActivity );
        dietSpinnercall = findViewById ( R.id.spinDiet );
        Toast.makeText ( this, "hello", Toast.LENGTH_SHORT ).show ();
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( this ).getUser ().getToken ();
        token = "token " + tempToken;

        String namecall = nameVal.getText ().toString ().trim ();
        String weightString = weightcall.getText ().toString ().trim ();
        String heightString = heightcall.getText ().toString ().trim ();
        String ageString = agecall.getText ().toString ().trim ();
        int gen =genSpinnercall.getSelectedItemPosition ();
        int goa = goalSpinnercall.getSelectedItemPosition ();
        int act = activitySpinnercall.getSelectedItemPosition ();
        int foodChoiceCall = dietSpinnercall.getSelectedItemPosition ();
        String gendercall, goalscall, activitycall;
        gendercall = Integer.toString ( gen );
        goalscall = Integer.toString ( goa );
        activitycall = Integer.toString ( act );


        if (namecall.isEmpty()) {
            nameVal.setError("What should we call you?");
            nameVal.requestFocus();
            return;
        }
        if (weightString.isEmpty()) {
            weightcall.setError("Please enter your weight to calculate BMI");
            weightcall.requestFocus();
            return;
        }
        if (heightString.isEmpty()) {
            heightcall.setError("Please enter your height to calculate BMI");
            heightcall.requestFocus();
            return;
        }
        if(ageString.isEmpty ()){
            agecall.setError ( "Please enter your age for relevant suggestions" );
            agecall.requestFocus ();
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
        if(foodChoiceCall==0)
        {
            Toast.makeText ( this, "Please tell us about your diet preference", Toast.LENGTH_LONG ).show ();
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
        Call<EditProfileResponse>  call = RetrofitClient.getInstance ().getApi ().updateInfo ( token,gendercall,weightString,heightString,goalscall,activitycall,ageString,namecall,foodChoiceCall,lactoseIntolerance );
        call.enqueue ( new Callback<EditProfileResponse> () {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if(response.body ().isSucess ()){
                    Toast.makeText ( EditProfile.this, "Profile Updated Successfully", Toast.LENGTH_LONG ).show ();
                }
                else
                    Toast.makeText ( EditProfile.this, "Please try again", Toast.LENGTH_LONG ).show ();
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
//                Toast.makeText ( EditProfile.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();

            }
        } );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.updatebtn:
                hello ();
                break;

        }

    }
}
