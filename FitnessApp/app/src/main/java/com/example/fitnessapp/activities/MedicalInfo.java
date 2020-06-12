package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.MedicalDataResponse;
import com.example.fitnessapp.model_class.MultipleChoiceFragment;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, MultipleChoiceFragment.onMultiChoiceListener {
    private Spinner bloodGroupSpinner;
    private EditText description;
    private TextView tvSelectedChoicses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        setContentView ( R.layout.activity_medical_info );
        getSupportActionBar ().hide ();
        findViewById ( R.id.msavebtn ).setOnClickListener ( this );
        bloodGroupSpinner = findViewById(R.id.spinBloodGroup);
        bloodGroupSpinner.setOnItemSelectedListener(this);
        description = findViewById ( R.id.description );
        tvSelectedChoicses = findViewById ( R.id.tvSelectedChoices );
        Button selectDisease = findViewById ( R.id.diseaseButton );
        selectDisease.setOnClickListener ( this );

    }

    private void medicalStats(){
        String tempToken = "";
        String token = "";
        tempToken =  SharedPreferenceManager.getInstance ( MedicalInfo.this ).getUser ().getToken ();
        token = "token " + tempToken;
        int bloodGroup =bloodGroupSpinner.getSelectedItemPosition ();
        String desc = description.getText ().toString ().trim ();
        if (desc.isEmpty()) {
            description.setError("Please tell us about yourself");
            description.requestFocus();
            return;
        }
        int prob1= 1;
        int prob2=2;
        int prob3=4;


        Call<MedicalDataResponse> call =  RetrofitClient.getInstance ().getApi ().getMedicalInfo ( token,bloodGroup,desc,prob1,prob2,prob3 );
        call.enqueue ( new Callback<MedicalDataResponse> () {
            @Override
            public void onResponse(Call<MedicalDataResponse> call, Response<MedicalDataResponse> response) {
                MedicalDataResponse response1 = response.body ();
                if (response1 != null) {
                    if (response1.getSucess ()) {
                        Toast.makeText ( MedicalInfo.this, "Done succesfully", Toast.LENGTH_SHORT ).show ();
                        Intent intent = new Intent ( MedicalInfo.this, DashboardActivity.class );
                        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( intent );
                    } else {
                        Toast.makeText ( MedicalInfo.this, "error", Toast.LENGTH_SHORT ).show ();
                    }
                }
            }
            @Override
            public void onFailure(Call<MedicalDataResponse> call, Throwable t) {
                Toast.makeText ( MedicalInfo.this, t.getMessage (), Toast.LENGTH_SHORT ).show ();

            }
        } );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.msavebtn:
                medicalStats();
                break;
            case R.id.diseaseButton:
                DialogFragment multiChoiceDialog = new MultipleChoiceFragment ();
                multiChoiceDialog.setCancelable ( false );
                multiChoiceDialog.show ( getSupportFragmentManager (),  "MultiChoiceDialog");

        }

    }
    @Override
    protected void onStart() {
        super.onStart ();
        if(SharedPreferenceManager.getInstance ( this ).isMedical ()){
            Intent intent = new Intent ( this, DashboardActivity.class );
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

    @Override
    public void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemList) {
        StringBuilder stringBuilder = new StringBuilder (  );
        stringBuilder.append ( "Selected Choices = " );
        for(String str:selectedItemList){
            stringBuilder.append ( str+" " );
            tvSelectedChoicses.setText ( stringBuilder );
        }
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
