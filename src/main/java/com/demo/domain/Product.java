package com.demo.domain;

import javax.persistence.Entity;

/**
 * Created by anton_kramarev on 8/2/2016.
 */
@Entity
public class Product extends MenuComponent {

    private String name;
    private int protein;
    private int fat;
    private int carbohydrate;
    private int calories;

    public Product() {
    }

    public Product(String name, int protein, int fat, int carbohydrate, int calories) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.calories = calories;
    }

    @Override
    public int getTotalProtein() {
        return getProtein();
    }

    @Override
    public int getTotalFat() {
        return getFat();
    }

    @Override
    public int getTotalCarbohydrate() {
        return getCarbohydrate();
    }

    @Override
    public int getTotalCalories() {
        return getCalories();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                ", calories=" + calories +
                '}';
    }
}
