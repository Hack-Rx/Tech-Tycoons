package com.example.fitnessapp.model_class;

import com.google.gson.annotations.SerializedName;

public class DashboardResponse {
    private String sucess;
    private int status;
    @SerializedName( "data" )
    private Statistics statistics;

    public DashboardResponse(String sucess, int status, Statistics statistics) {
        this.sucess = sucess;
        this.status = status;
        this.statistics = statistics;
    }

    public String getSucess() {
        return sucess;
    }

    public int getStatus() {
        return status;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
