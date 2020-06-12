package com.example.fitnessapp.api;

import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://kanishkarrevin.pythonanywhere.com/";
    private static RetrofitClient mInstanceSignUp;
    private Retrofit retrofitSignUp;
    private RetrofitClient(){
        retrofitSignUp = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance(){
        if(mInstanceSignUp == null){
            mInstanceSignUp = new RetrofitClient();

        }
        return mInstanceSignUp;
    }
    public Api getApi(){
        return  retrofitSignUp.create(Api.class);
    }
}
