package com.example.fitnessapp.activities;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.example.fitnessapp.storage.SharedPreferenceManager;


public class MainActivity extends AppCompatActivity  {
    private static int SPLASH_SCREEN = 5000;

    Animation topAnimation, bottomAnimation;
    ImageView logo, design;
    TextView name, tagline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature( Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView( R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation ( this, R.anim.top_animation );
        bottomAnimation = AnimationUtils.loadAnimation ( this, R.anim.bottom_animation );
        logo = findViewById ( R.id.logo );
        design = findViewById ( R.id.design );
        name = findViewById ( R.id.name );
        tagline = findViewById ( R.id.tagline );

        logo.setAnimation ( topAnimation );
        name.setAnimation ( topAnimation );
        tagline.setAnimation ( bottomAnimation );
        design.setAnimation ( bottomAnimation );

       new Handler (  ).postDelayed ( new Runnable () {
           @Override
           public void run() {
               Intent intent = new Intent ( MainActivity.this, LoginActivity.class );

               Pair[] pairs = new Pair[2];
               pairs[0] = new Pair<View,String>(logo, "logo_trans");
               pairs[1] = new Pair<View,String>(tagline, "text_trans");

               if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                   ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation ( MainActivity.this, pairs );
                   startActivity ( intent, options.toBundle () );
                   finish ();
               }

           }
       }, SPLASH_SCREEN );


    }
//    @Override
//    protected void onStart() {
//        super.onStart ();
//        if(SharedPreferenceManager.getInstance ( this ).isLoggedIn ()){
//
//            Intent intent = new Intent ( this, UserStatistics.class );
//            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//            startActivity ( intent );
//        }
//    }

}
