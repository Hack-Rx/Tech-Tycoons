package com.example.fitnessapp.model_class;

public class Statistics {
    private String gender;
    private double weight;
    private double height;
    private String goals;
    private String activity;
    private int age;
    private String username;
    private double bmi;
    private int dailyCalories;
    private double bmr;
    private  String condition;
    private String foodChoice;
    private Boolean lactoseIntolerance;
    private String name;

    public Statistics(String gender, double weight, double height, String goals, String activity, int age, String username, double bmi, int dailyCalories, double bmr, String condition, String foodChoice, Boolean lactoseIntolerance, String name) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.goals = goals;
        this.activity = activity;
        this.age = age;
        this.username = username;
        this.bmi = bmi;
        this.dailyCalories = dailyCalories;
        this.bmr = bmr;
        this.condition = condition;
        this.foodChoice = foodChoice;
        this.lactoseIntolerance = lactoseIntolerance;
        this.name=name;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
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

    public String getUsername() {
        return username;
    }

    public double getBmi() {
        return bmi;
    }

    public int getDailyCalories() {
        return dailyCalories;
    }

    public double getBmr() {
        return bmr;
    }

    public String getCondition() {
        return condition;
    }

    public String getFoodChoice() {
        return foodChoice;
    }

    public Boolean getLactoseIntolerance() {
        return lactoseIntolerance;
    }

    public String getName() {
        return name;
    }
}
