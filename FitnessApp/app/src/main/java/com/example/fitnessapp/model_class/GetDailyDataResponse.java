package com.example.fitnessapp.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDailyDataResponse {
    @SerializedName("sucess")
    @Expose
    private Boolean sucess;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("calories took")
    @Expose
    private Integer caloriesTook;
    @SerializedName("calories left")
    @Expose
    private Integer caloriesLeft;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCaloriesTook() {
        return caloriesTook;
    }

    public void setCaloriesTook(Integer caloriesTook) {
        this.caloriesTook = caloriesTook;
    }

    public Integer getCaloriesLeft() {
        return caloriesLeft;
    }

    public void setCaloriesLeft(Integer caloriesLeft) {
        this.caloriesLeft = caloriesLeft;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
