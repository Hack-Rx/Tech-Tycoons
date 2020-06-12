package com.example.fitnessapp.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodQuery {
    @SerializedName ( "id" )
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Food_Group")
    @Expose
    private String foodGroup;
    @SerializedName("Unitconversion")
    @Expose
    private String unitconversion;
    @SerializedName("Calories")
    @Expose
    private Integer calories;
    @SerializedName("Lactose_Intolerance")
    @Expose
    private Boolean lactoseIntolerance;
    @SerializedName("Fat")
    @Expose
    private String fat;
    @SerializedName("Protein")
    @Expose
    private String protein;
    @SerializedName("Carbohydrate")
    @Expose
    private String carbohydrate;
    @SerializedName("Vitamin")
    @Expose
    private String vitamin;
    @SerializedName("Sugars")
    @Expose
    private String sugars;
    @SerializedName("Fiber")
    @Expose
    private String fiber;
    @SerializedName("Cholesterol")
    @Expose
    private String cholesterol;
    @SerializedName("Saturated_Fats")
    @Expose
    private String saturatedFats;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Availablity")
    @Expose
    private List<Availablity> availablity = null;
    @SerializedName("Problems_Can_Solve")
    @Expose
    private List<ProblemsCanSolve> problemsCanSolve = null;
    @SerializedName("Processing_level")
    @Expose
    private String processingLevel;
    @SerializedName("AvailablityTier")
    @Expose
    private String availablityTier;
    @SerializedName("Category")
    @Expose
    private String category;

    public FoodQuery(int id, String name, String description, String foodGroup, String unitconversion, Integer calories, Boolean lactoseIntolerance, String fat, String protein, String carbohydrate, String vitamin, String sugars, String fiber, String cholesterol, String saturatedFats, String image, List<Availablity> availablity, List<ProblemsCanSolve> problemsCanSolve, String processingLevel, String availablityTier, String category) {
       this.id=id;
        this.name = name;
        this.description = description;
        this.foodGroup = foodGroup;
        this.unitconversion = unitconversion;
        this.calories = calories;
        this.lactoseIntolerance = lactoseIntolerance;
        this.fat = fat;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.vitamin = vitamin;
        this.sugars = sugars;
        this.fiber = fiber;
        this.cholesterol = cholesterol;
        this.saturatedFats = saturatedFats;
        this.image = image;
        this.availablity = availablity;
        this.problemsCanSolve = problemsCanSolve;
        this.processingLevel = processingLevel;
        this.availablityTier = availablityTier;
        this.category = category;
    }
    public int getId(){return id;}

    public void setId(int id){this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public String getUnitconversion() {
        return unitconversion;
    }

    public void setUnitconversion(String unitconversion) {
        this.unitconversion = unitconversion;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Boolean getLactoseIntolerance() {
        return lactoseIntolerance;
    }

    public void setLactoseIntolerance(Boolean lactoseIntolerance) {
        this.lactoseIntolerance = lactoseIntolerance;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getVitamin() {
        return vitamin;
    }

    public void setVitamin(String vitamin) {
        this.vitamin = vitamin;
    }

    public String getSugars() {
        return sugars;
    }

    public void setSugars(String sugars) {
        this.sugars = sugars;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getSaturatedFats() {
        return saturatedFats;
    }

    public void setSaturatedFats(String saturatedFats) {
        this.saturatedFats = saturatedFats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Availablity> getAvailablity() {
        return availablity;
    }

    public void setAvailablity(List<Availablity> availablity) {
        this.availablity = availablity;
    }

    public List<ProblemsCanSolve> getProblemsCanSolve() {
        return problemsCanSolve;
    }

    public void setProblemsCanSolve(List<ProblemsCanSolve> problemsCanSolve) {
        this.problemsCanSolve = problemsCanSolve;
    }

    public String getProcessingLevel() {
        return processingLevel;
    }

    public void setProcessingLevel(String processingLevel) {
        this.processingLevel = processingLevel;
    }

    public String getAvailablityTier() {
        return availablityTier;
    }

    public void setAvailablityTier(String availablityTier) {
        this.availablityTier = availablityTier;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}