package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.MedicalResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalProfile extends AppCompatActivity {
    private TextView description;
    private TextView bloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_medical_profile );
        description = findViewById ( R.id.description );
        bloodGroup = findViewById ( R.id.bloodGroup );
        String tempToken = "";
        String token = "";
        tempToken = SharedPreferenceManager.getInstance ( this ).getUser ().getToken ();
        token = "token " + tempToken;
        Call<MedicalResponse> call = RetrofitClient.getInstance ().getApi ().medicalData ( token );
        call.enqueue ( new Callback<MedicalResponse> () {
            @Override
            public void onResponse(Call<MedicalResponse> call, Response<MedicalResponse> response) {
                if(response.body ().getSucess ()){
                    String bg="";
                    String desc="";

                    bg = response.body ().getData ().getBloodGroup ();
                    desc = response.body ().getData ().getDescription ();

                    description.setText ( desc );
                    bloodGroup.setText ( bg );

                }
            }

            @Override
            public void onFailure(Call<MedicalResponse> call, Throwable t) {

            }
        } );
    }
}
