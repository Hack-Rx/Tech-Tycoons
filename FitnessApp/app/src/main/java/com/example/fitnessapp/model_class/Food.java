package com.example.fitnessapp.model_class;

public class Food {
    private String name;
    private String Calories;
    private String Fat;
    private String Protein;
    private String Carbohydrate;
    private String Image;
//    private float Cholestrol;


    public Food(String name, String calories, String fat, String protein, String carbohydrate, String image) {
        this.name = name;
        Calories = calories;
        Fat = fat;
        Protein = protein;
        Carbohydrate = carbohydrate;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return Calories;
    }

    public String getFat() {
        return Fat;
    }

    public String getProtein() {
        return Protein;
    }

    public String getCarbohydrate() {
        return Carbohydrate;
    }

    public String getImage() {
        return Image;
    }
}


