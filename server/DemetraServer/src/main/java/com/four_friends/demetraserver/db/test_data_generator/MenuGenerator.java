/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.food_params.FoodCategory;
import com.four_friends.demetraserver.entity.food_params.FoodSize;
import com.four_friends.demetraserver.entity.food_params.Menu;
import com.four_friends.demetraserver.entity.food_params.MenuPosition;
import com.four_friends.demetraserver.entity.food_params.Topping;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gekko
 */
public class MenuGenerator {

    public static List<Topping> createSoupTopping() {
        List<Topping> soupToppings = new ArrayList<>();
        Topping crackersTopping = new Topping();
        crackersTopping.setId(21L);
        crackersTopping.setName("Сухарики");
        crackersTopping.setName("Сухарики из белого хлеба");
        crackersTopping.setPrice(15.0f);
        
        Topping extraMeatTopping = new Topping();
        extraMeatTopping.setId(22L);
        extraMeatTopping.setName("Дополнительное мясо");
        extraMeatTopping.setPrice(45.0f);
        
        Topping sosiageTopping = new Topping();
        sosiageTopping.setId(23L);
        sosiageTopping.setName("Сосиски");
        sosiageTopping.setPrice(35.0f);
        
        soupToppings.add(crackersTopping);
        soupToppings.add(extraMeatTopping);
        soupToppings.add(sosiageTopping);
        return soupToppings;
    }
    public static List<FoodSize> createPizzaFoodSizes(){
        List<FoodSize> foodSizes = new ArrayList<>();
        
        FoodSize foodSizeSmall = new FoodSize();
        foodSizeSmall.setId(31L);
        foodSizeSmall.setName("Маленькая");
        foodSizeSmall.setPrice(56.0f);
        
        FoodSize foodSizeKingSize = new FoodSize();
        foodSizeKingSize.setId(32L);
        foodSizeKingSize.setName("Королевский размер");
        foodSizeKingSize.setPrice(777.0f);
        
        foodSizes.add(foodSizeSmall);
        foodSizes.add(foodSizeKingSize);
        return foodSizes;
    }
    
    public static FoodCategory createSoupFoodCategory() {
        FoodCategory foodCategorySoup = new FoodCategory("Супы");
        foodCategorySoup.setId(1);
        return foodCategorySoup;
    }
    public static FoodCategory createCategoryPizza() {
        FoodCategory foodCategoryPizza = new FoodCategory("Пицца");
        foodCategoryPizza.setId(2);
        return foodCategoryPizza;
    }
   
    public static Menu createMenu(){
        
        FoodCategory soupFoodCategory = createSoupFoodCategory();
        FoodCategory pizzaFoodCategory = createCategoryPizza();
        Menu menu = new Menu();
        
        MenuPosition menuPositionBorsht = new MenuPosition("Борщ");
        menuPositionBorsht.setDescription("Борщ");
        menuPositionBorsht.setId(41L);
        List<Topping> createSoupTopping = createSoupTopping();
        for (Topping soupTopping : createSoupTopping) {
            menuPositionBorsht.addTopping(soupTopping);
        }
        menuPositionBorsht.setFoodCategory(soupFoodCategory);
        
        MenuPosition menuPositionShi = new MenuPosition("Щи");
        menuPositionShi.setDescription("Щи");
        menuPositionShi.setId(42L);
        for (Topping soupTopping : createSoupTopping) {
            menuPositionShi.addTopping(soupTopping);
        }
        menuPositionShi.setFoodCategory(soupFoodCategory);
    
        MenuPosition menuPositionQuadroFormaggi = new MenuPosition("Пицца 4 сыра");
        menuPositionQuadroFormaggi.setId(43L);
        List<FoodSize> createPizzaFoodSizes = createPizzaFoodSizes();
        for (FoodSize foodSize : createPizzaFoodSizes) {
            menuPositionQuadroFormaggi.addFoodSize(foodSize);
        }
        menuPositionQuadroFormaggi.setFoodCategory(pizzaFoodCategory);
        
        menu.addMenuPosition(menuPositionBorsht);
        menu.addMenuPosition(menuPositionShi);
        menu.addMenuPosition(menuPositionQuadroFormaggi);
        return menu;
    }

}
