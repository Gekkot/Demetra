package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gekko
 */
public class MenuPosition extends Entity {

    public MenuPosition() {
    }

    public MenuPosition(String name) {
        this.name = name;
    }
    
    
    @DatabaseField
    String name;
    
    @DatabaseField
    String description;
    
    @DatabaseField
    float price;
    
    FoodCategory foodCategory;
    
    List<Topping> toppings  = new ArrayList<>();
    
    @DatabaseField
    Long[] toppingsIds = new Long[]{};
    
    @DatabaseField
    String imageUrl;
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
    
    
    
    
}
