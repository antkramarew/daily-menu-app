package com.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anton_kramarev on 8/2/2016.
 */

@Entity
public class DailyMenu extends MenuComponent{

    private Date date;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MenuComponent> components = new ArrayList<>();

    @Override
    public void add(MenuComponent component) {
       components.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        components.remove(component);
    }

    @Override
    public MenuComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public int getTotalProtein() {
        int total = 0;
        for (MenuComponent component : components) {
            total += component.getTotalProtein();
        }
        return total;
    }

    @Override
    public int getTotalFat() {
        int total = 0;
        for (MenuComponent component : components) {
            total += component.getTotalFat();
        }
        return total;
    }

    @Override
    public int getTotalCarbohydrate() {
        int total = 0;
        for (MenuComponent component : components) {
            total += component.getTotalCarbohydrate();
        }
        return total;
    }

    @Override
    public int getTotalCalories() {
        int total = 0;
        for (MenuComponent component : components) {
            total += component.getTotalCalories();
        }
        return total;
    }

    @Override
    public int getTotalAmount() {
        int total = 0;
        for (MenuComponent component : components) {
            total += component.getTotalAmount();
        }
        return total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<MenuComponent> getComponents() {
        return components;
    }

    public void setComponents(List<MenuComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        components.forEach(menuComponent -> buffer.append(menuComponent));
        return "DailyMenu{" +
                "date=" + date +
                ", components=" + buffer +
                '}';
    }
}
