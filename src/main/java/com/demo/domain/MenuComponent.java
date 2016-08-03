package com.demo.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by anton_kramarev on 8/2/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MenuComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }


    public int getTotalProtein() {
        throw new UnsupportedOperationException();
    }

    public int getTotalFat() {
        throw new UnsupportedOperationException();
    }

    public int getTotalCarbohydrate() {
        throw new UnsupportedOperationException();
    }

    public int getTotalCalories() {
        throw new UnsupportedOperationException();
    }

    public int getTotalAmount() {
        throw new UnsupportedOperationException();
    }

    public List<MenuComponent> getChildren(){
        throw new UnsupportedOperationException();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
