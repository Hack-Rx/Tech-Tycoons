package com.example.fitnessapp.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoMedical {

    @SerializedName("bloodGroup")
    @Expose
    private Integer bloodGroup;
    @SerializedName("problem")
    @Expose
    private List<Integer> problem = null;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(Integer bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<Integer> getProblem() {
        return problem;
    }

    public void setProblem(List<Integer> problem) {
        this.problem = problem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
