package com.example.fitnessapp.model_class;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private boolean success;
    private int response;
    private  String error_message;

    @SerializedName ( "data" )
    private User user;

    /*
     * {"response":200,"success":true,"error_message":"sucessfull login","data":{"token":"85b56a7a60ac775552f773e0b44f17527f0b5477","username":"kanishka","email":"kani123@gmail.com","id":5}}
     * */

    public LoginResponse(boolean success, int response, String error_message, User user) {
        this.success = success;
        this.response = response;
        this.error_message = error_message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getResponse() {
        return response;
    }

    public String getError_message() {
        return error_message;
    }

    public User getUser() {
        return user;
    }
}
