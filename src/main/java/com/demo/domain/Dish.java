package com.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_kramarev on 8/2/2016.
 */

@Entity
public class Dish extends MenuComponent {

    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductItem> ingredients = new ArrayList<>();

    private int amount;

    @Override
    public void add(MenuComponent component) {
        ingredients.add((ProductItem) component);
    }

    @Override
    public void remove(MenuComponent component) {
        ingredients.remove((ProductItem) component);
    }

    @Override
    public MenuComponent getChild(int index) {
        return ingredients.get(index);
    }

    @Override
    public int getTotalProtein() {
        int total = 0;
        for (ProductItem ingredient : ingredients) {
            total += ingredient.getTotalProtein();
        }
        return total;
    }

    @Override
    public int getTotalFat() {
        int total = 0;
        for (ProductItem ingredient : ingredients) {
            total += ingredient.getTotalFat();
        }
        return total;
    }

    @Override
    public int getTotalCarbohydrate() {
        int total = 0;
        for (ProductItem ingredient : ingredients) {
            total += ingredient.getTotalCarbohydrate();
        }
        return total;
    }

    @Override
    public int getTotalCalories() {
        int total = 0;
        for (ProductItem ingredient : ingredients) {
            total += ingredient.getTotalCalories();
        }
        return total;
    }

    @Override
    public int getTotalAmount() {
        return ingredients.stream().mapToInt(value -> value.getAmount()).sum();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        ingredients.forEach(ingredient -> buffer.append(ingredient));

        return "Dish{" +
                "ingredients=" + buffer +
                '}';
    }
}
