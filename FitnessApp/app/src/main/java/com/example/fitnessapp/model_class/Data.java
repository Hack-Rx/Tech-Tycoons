package com.example.fitnessapp.model_class;

public class Data {
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

    public Data(String gender, float weight, float height, String goals, String activity, int age, float bmi, int condition, String name, int foodChoice, Boolean lactoseIntolerance) {
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoodChoice() {
        return foodChoice;
    }

    public void setFoodChoice(int foodChoice) {
        this.foodChoice = foodChoice;
    }

    public Boolean getLactoseIntolerance() {
        return lactoseIntolerance;
    }

    public void setLactoseIntolerance(Boolean lactoseIntolerance) {
        this.lactoseIntolerance = lactoseIntolerance;
    }
}


    //

//    public Data(String gender, int weight, int height, String goals, String activity, int age, int BMI, int condition) {
//        Gender = gender;
//        Weight = weight;
//        Height = height;
//        Goals = goals;
//        Activity = activity;
//        Age = age;
//        this.BMI = BMI;
//        Condition = condition;
//    }

//    public String getGender() {
//        return Gender;
//    }
//
//    public int getWeight() {
//        return Weight;
//    }
//
//    public int getHeight() {
//        return Height;
//    }
//
//    public String getGoals() {
//        return Goals;
//    }
//
//    public String getActivity() {
//        return Activity;
//    }
//
//    public int getAge() {
//        return Age;
//    }
//
//    public int getBMI() {
//        return BMI;
//    }
//
//    public int getCondition() {
//        return Condition;
//    }

