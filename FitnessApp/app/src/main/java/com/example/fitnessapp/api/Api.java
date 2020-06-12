package com.example.fitnessapp.api;

import com.example.fitnessapp.model_class.DashboardResponse;
import com.example.fitnessapp.model_class.EditProfileResponse;
import com.example.fitnessapp.model_class.Food;
import com.example.fitnessapp.model_class.FoodQuery;
import com.example.fitnessapp.model_class.LoginResponse;
import com.example.fitnessapp.model_class.MedicalDataResponse;
import com.example.fitnessapp.model_class.MedicalResponse;
import com.example.fitnessapp.model_class.UserStatsResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("account/registration/")
    Call<ResponseBody> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password
    );

    @FormUrlEncoded
    @POST("/account/login/")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("info/profile/")
    Call<UserStatsResponse> userInfo(
            @Header("Authorization") String token,
            @Field("gender") String gender,
            @Field("weight") int weight,
            @Field("height") int height,
            @Field("goals") String goals,
            @Field("activity") String activity,
            @Field("age") int age,
            @Field("name") String name,
            @Field("foodChoice") int foodChoice,
            @Field("lactoseIntolerance") Boolean lactoseIntolerance

    );
    @GET("food/get-recoomended-food/")
    Call<List<Food>> getFood(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("info/Medicalform/")
    Call<MedicalDataResponse> getMedicalInfo(
            @Header("Authorization") String token,
            @Field ( "bloodGroup" ) int bloodGroup,
            @Field ( "Description" ) String description,
            @Field ( "problem" ) int prob1,
            @Field ( "problem" ) int prob2,
            @Field ( "problem" ) int prob3
            );
    @GET("info/profile/")
    Call<DashboardResponse> getInfo(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("info/profile/")
    Call<EditProfileResponse> updateInfo(
            @Header("Authorization") String token,
            @Field("gender") String gender,
            @Field("weight") String weight,
            @Field("height") String height,
            @Field("goals") String goals,
            @Field("activity") String activity,
            @Field("age") String age,
            @Field("name") String name,
            @Field("foodChoice") int foodChoice,
            @Field("lactoseIntolerance") Boolean lactoseIntolerance);
    @GET("food/list-of-food/")
    Call<FoodQuery> getFoodInfo(@Header("Authorization") String token,
                                @Query("search") String foodItemNAme);
    @GET("info/Medicalform/")
    Call<MedicalResponse> medicalData(
            @Header("Authorization") String token
    );
}
