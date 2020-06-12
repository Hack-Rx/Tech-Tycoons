package com.example.fitnessapp.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicalData {

    @SerializedName("bloodGroup")
    @Expose
    private String bloodGroup;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("problem")
    @Expose
    private List<MedicalProblem> problem = null;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MedicalProblem> getProblem() {
        return problem;
    }

    public void setProblem(List<MedicalProblem> problem) {
        this.problem = problem;
    }

}
