package com.four_friends.demetraserver.entity.food_params;

import com.four_friends.demetraserver.entity.Entity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gekko
 */
@DatabaseTable
public class FoodCategory extends Entity {
    
    @DatabaseField
    String name;
    
    @DatabaseField
    String description;
    
    @DatabaseField
    Long[] toppingIds = new Long[]{};
    
    List<Topping> toppings = new ArrayList<>();
   
   
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
        setToppingIds(updatedeToppingIds);
    }

    public FoodCategory() {
        this("","");
    }

    public FoodCategory(String name) {
        this(name, "");
    }

    public FoodCategory(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Long[] getToppingIds() {
        return toppingIds;
    }

    public void setToppingIds(Long[] toppingIds) {
        this.toppingIds = toppingIds;
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
    
    
}
