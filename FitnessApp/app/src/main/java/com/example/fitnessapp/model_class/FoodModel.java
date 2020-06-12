package com.example.fitnessapp.model_class;

public class FoodModel {
    Integer imageicon;
    String label;
    String values;

    public FoodModel(Integer imageicon, String label, String values) {
        this.imageicon = imageicon;
        this.label = label;
        this.values = values;
    }

    public Integer getImageicon() {
        return imageicon;
    }

    public String getLabel() {
        return label;
    }

    public String getValues() {
        return values;
    }
}
