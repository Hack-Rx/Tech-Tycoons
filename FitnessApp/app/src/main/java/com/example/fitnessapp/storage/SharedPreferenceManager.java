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

    public User getUser() { mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
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
