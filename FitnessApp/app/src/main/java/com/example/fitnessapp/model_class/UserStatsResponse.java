package com.example.fitnessapp.model_class;

public class UserStatsResponse {
    private boolean sucess;
    private int status;
    private Data data;

    public UserStatsResponse(boolean sucess, int status, Data data) {
        this.sucess = sucess;
        this.status = status;
        this.data = data;
    }

    public boolean isSucess() {
        return sucess;
    }

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }
}
