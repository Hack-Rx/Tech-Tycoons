package com.example.fitnessapp.storage;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.fitnessapp.model_class.Data;
import com.example.fitnessapp.model_class.User;

public class SharedPreferenceManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static SharedPreferenceManager mInstance;
    private Context mCtx;
    private SharedPreferenceManager(Context mCtx) {
        this.mCtx = mCtx;
    }
    public static synchronized SharedPreferenceManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(mCtx);
        }
        return mInstance;
    }

    public void saveMedical(InfoMedical medical){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt ( "bloodGroup", medical.getBloodGroup () );
        editor.putString ( "description" , medical.getDescription ());

    }
    public boolean isMedical(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt ("bloodGroup", 0) != 0;
    }

    public void saveStatistics(Data data){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString ( "gender" , data.getGender ());
        editor.putFloat ( "weight", data.getWeight () );
        editor.putFloat ( "height", data.getHeight () );
        editor.putString ( "goals", data.getGoals () );
        editor.putString ( "activity", data.getActivity () );
        editor.putInt ( "age", data.getAge () );
        editor.putFloat ( "bmi", data.getBmi ());
        editor.putInt ( "condition", data.getCondition () );
        editor.putString ( "name", data.getName () );
        editor.putInt ( "foodChoice", data.getFoodChoice () );
        editor.putBoolean ( "lactoseIntolerance", data.getLactoseIntolerance () );
        editor.apply ();
    }

    public boolean isSaved(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString ("gender", null) != null;
    }

    public Data getData(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Data(
                sharedPreferences.getString ( "gender", null ),
                sharedPreferences.getFloat ( "weight", -1 ),
                sharedPreferences.getFloat ( "height", -1 ),
                sharedPreferences.getString ( "goals", null ),
                sharedPreferences.getString ( "activity", null ),
                sharedPreferences.getInt ( "age", -1 ),
                sharedPreferences.getFloat ( "bmi",-1 ),
                sharedPreferences.getInt ( "condition",-1 ),
                sharedPreferences.getString ( "name", null ),
                sharedPreferences.getInt ( "foodChoice", -1 ),
                sharedPreferences.getBoolean ( "lactoseIntolerance", false )
        );
    }


    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", user.getToken ()); //user object is null here simple nullpointerexception
        editor.putString("username", user.getUsername ());
        editor.putString("email", user.getEmail());
        editor.putInt("id", user.getId());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString ("token", null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("token", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getInt("id", -1)

        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove ( "token" );
        editor.apply();
    }
}
