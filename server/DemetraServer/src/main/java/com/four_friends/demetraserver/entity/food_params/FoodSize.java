package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author gekko
 */
public class FoodSize extends Entity {

    public FoodSize() {
    }

    @DatabaseField
    String name;
    
    @DatabaseField
    float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
