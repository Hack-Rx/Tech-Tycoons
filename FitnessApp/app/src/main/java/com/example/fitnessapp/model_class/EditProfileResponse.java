package com.example.fitnessapp.model_class;

import com.google.gson.annotations.SerializedName;

public class EditProfileResponse {

    private boolean sucess;
    private int status;
    @SerializedName( "data" )
    private Udata udata;

    public EditProfileResponse(boolean sucess, int status, Udata udata) {
        this.sucess = sucess;
        this.status = status;
        this.udata = udata;
    }

    public boolean isSucess() {
        return sucess;
    }

    public int getStatus() {
        return status;
    }

    public Udata getUdata() {
        return udata;
    }
}
