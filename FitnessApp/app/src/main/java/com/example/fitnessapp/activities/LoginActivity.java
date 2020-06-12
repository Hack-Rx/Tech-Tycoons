package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.model_class.LoginResponse;
import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.storage.SharedPreferenceManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText editTextUsername, editTextPassword;
    TextView  welcome,descendant,callSignUp;
    ImageView logo;
    Button  login;


//try

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar ().hide (); // hide the title bar
        setContentView ( R.layout.activity_login );

        editTextUsername = findViewById ( R.id.luname );
        editTextPassword = findViewById ( R.id.lpassword );

        findViewById ( R.id.loginbtn ).setOnClickListener ( this );
        findViewById ( R.id.signupclick ).setOnClickListener ( this );
        logo = findViewById ( R.id.logo );
        welcome = findViewById ( R.id.welcome );
        descendant = findViewById ( R.id.desc );
        callSignUp = findViewById ( R.id.signupclick );
        login = findViewById ( R.id.loginbtn );


    }


//        else if(!SharedPreferenceManager.getInstance ( this ).isLoggedIn () && SharedPreferenceManager.getInstance ( this ).isSaved () ){
//            Intent intent = new Intent ( this, DashboardActivity.class );
//            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//            startActivity ( intent );
//        }
//    }


    private void userLogin() {

        String username = editTextUsername.getText ().toString ().trim ();
        String password = editTextPassword.getText ().toString ().trim ();

        if (username.isEmpty ()) {
            editTextUsername.setError ( "Username is required!" );
            editTextUsername.requestFocus ();
            return;
        }
        if (password.isEmpty ()) {
            editTextPassword.setError ( "Password is required!" );
            editTextPassword.requestFocus ();
            return;
        }
        if (password.length () < 8) {
            editTextPassword.setError ( "Password should be atleast 8 characters long" );
            editTextPassword.requestFocus ();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance ().getApi ().userLogin ( username, password );
        call.enqueue ( new Callback<LoginResponse> () {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body ();
//                Log.d("error", String.valueOf ( loginResponse ) );
                if (loginResponse != null) {
                    if (loginResponse.isSuccess ()) {
                        String token = "";

                        Toast.makeText ( LoginActivity.this, loginResponse.getError_message (), Toast.LENGTH_LONG ).show ();
                        SharedPreferenceManager.getInstance ( LoginActivity.this ).saveUser (loginResponse.getUser () );

                        token = SharedPreferenceManager.getInstance ( LoginActivity.this ).getUser ().getToken ();
//                        Toast.makeText ( LoginActivity.this, token, Toast.LENGTH_LONG ).show ();

                        Intent intent = new Intent ( LoginActivity.this, UserStatistics.class );
                        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( intent );

                    } else {
                        Toast.makeText ( LoginActivity.this, "Login failed", Toast.LENGTH_LONG ).show ();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        } );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.loginbtn:
                userLogin ();
                break;
            case R.id.signupclick:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(logo, "logo_trans");
                pairs[1] = new Pair<View, String>(welcome, "text_trans");
                pairs[2] = new Pair<View, String>(descendant, "desc_trans");
                pairs[3] = new Pair<View, String>(editTextUsername, "uname_trans");
                pairs[4] = new Pair<View, String>(editTextPassword, "password_trans");
                pairs[5] = new Pair<View, String>(login, "button_trans");
                pairs[6] = new Pair<View, String>(callSignUp, "login_signup_trans");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation ( LoginActivity.this, pairs );
                    startActivity (intent, options.toBundle ());
                    finish();
                    break;
                }


        }
    }
}