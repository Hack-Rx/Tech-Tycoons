package com.example.fitnessapp.model_class;

public class Udata {
    private String gender;
    private float weight;
    private float height;
    private String goals;
    private String activity;
    private int age;
    private float bmi;
    private int condition;
    private String name;
    private int foodChoice;
    private Boolean lactoseIntolerance;

    public Udata(String gender, float weight, float height, String goals, String activity, int age, float bmi, int condition, String name, int foodChoice, Boolean lactoseIntolerance) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goals = goals;
        this.activity = activity;
        this.age = age;
        this.bmi = bmi;
        this.condition = condition;
        this.name = name;
        this.foodChoice = foodChoice;
        this.lactoseIntolerance = lactoseIntolerance;
    }

    public String getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getGoals() {
        return goals;
    }

    public String getActivity() {
        return activity;
    }

    public int getAge() {
        return age;
    }

    public float getBmi() {
        return bmi;
    }

    public int getCondition() {
        return condition;
    }

    public String getName() {
        return name;
    }

    public int getFoodChoice() {
        return foodChoice;
    }

    public Boolean getLactoseIntolerance() {
        return lactoseIntolerance;
    }
}
