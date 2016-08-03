package com.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by anton_kramarev on 8/2/2016.
 */
@Entity
public class ProductItem extends MenuComponent {

    public static final int INITIAL_AMOUNT = 100;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
    private int amount;

    @Override
    public int getTotalProtein() {
        return getNutritionValue(product.getTotalProtein());
    }

    @Override
    public int getTotalFat() {
        return getNutritionValue(product.getTotalFat());
    }

    @Override
    public int getTotalCarbohydrate() {
        return getNutritionValue(product.getTotalCarbohydrate());
    }

    @Override
    public int getTotalCalories() {
        return getNutritionValue(product.getTotalCalories());
    }

    @Override
    public int getTotalAmount() {
        return getAmount();
    }

    public int getNutritionValue(int value) {
        return value * amount / INITIAL_AMOUNT;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}
