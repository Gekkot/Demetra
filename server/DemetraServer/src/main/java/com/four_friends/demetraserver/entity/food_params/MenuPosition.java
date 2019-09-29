package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gekko
 */
public class MenuPosition extends Entity {

    public MenuPosition() {
    }

    public MenuPosition(String name) {
        this.name = name;
        this.description = "";
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
    
    
    @DatabaseField
    Long[] foodSizeIds = new Long[]{};
    
    List<FoodSize> foodSizes = new ArrayList<>();
    
     public void addTopping(Topping topping){
        this.toppings.add(topping);
        updateToppingIds();
    }
    
    public void addFoodSize(FoodSize foodSize){
        this.foodSizes.add(foodSize);
        updateFoodSizeIds();
    }
    private void updateToppingIds() {
        Long[] updatedeToppingIds = this.toppings
                .stream()
                .filter(Objects::nonNull)
                .map((current_topping) -> {
                    return current_topping.getId();
                }).toArray(Long[]::new);
        setToppingsIds(updatedeToppingIds);
    }
    
    private void updateFoodSizeIds() {
         Long[] updatedeFoodSizeIds = this.foodSizes
                .stream()
                .filter(Objects::nonNull)
                .map((currentFoodsize) -> {
                    return currentFoodsize.getId();
                }).toArray(Long[]::new);
        setFoodSizeIds(updatedeFoodSizeIds);
    }

    public Long[] getFoodSizeIds() {
        return foodSizeIds;
    }

    public void setFoodSizeIds(Long[] foodSizeIds) {
        this.foodSizeIds = foodSizeIds;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public List<FoodSize> getFoodSizes() {
        return foodSizes;
    }

    public void setFoodSizes(List<FoodSize> foodSizes) {
        this.foodSizes = foodSizes;
    }
   

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

    public Long[] getToppingsIds() {
        return toppingsIds;
    }

    public void setToppingsIds(Long[] toppingsIds) {
        this.toppingsIds = toppingsIds;
    }
    
}
