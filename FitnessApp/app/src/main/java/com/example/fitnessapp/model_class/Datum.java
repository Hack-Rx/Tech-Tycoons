package com.example.fitnessapp.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("mark")
    @Expose
    private Boolean mark;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("calories")
    @Expose
    private Integer calories;

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
