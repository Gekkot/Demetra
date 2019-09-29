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

    
    public static List<Topping> createPizzaTopping(){
        List<Topping> pizzaToppings = new ArrayList<>();
        Topping shampinionsTopping = new Topping();
        shampinionsTopping.setDescription("Шампиньоны");
        shampinionsTopping.setId(121L);
        shampinionsTopping.setName("Шампиньоны");
        shampinionsTopping.setPrice(25.0f);
        pizzaToppings.add(shampinionsTopping);
        
        Topping pepperTopping = new Topping();
        pepperTopping.setName("Халапеньо");
        pepperTopping.setDescription("Острые перчики халапеньо");
        pepperTopping.setPrice(14.0f);
        pepperTopping.setId(122L);
        pizzaToppings.add(pepperTopping);
        return pizzaToppings;
        
    }
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
        foodSizeSmall.setPrice(0);
        
        FoodSize foodSizeKingSize = new FoodSize();
        foodSizeKingSize.setId(32L);
        foodSizeKingSize.setName("Королевский размер");
        foodSizeKingSize.setPrice(250.0f);
        
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
        menuPositionBorsht.setImageUrl("https://img03.rl0.ru/eda/c620x415i/s2.eda.ru/StaticContent/Photos/120213174921/1202131749503/p_O.jpg");
        menuPositionBorsht.setId(41L);
        List<Topping> createSoupTopping = createSoupTopping();
        for (Topping soupTopping : createSoupTopping) {
            menuPositionBorsht.addTopping(soupTopping);
        }
        menuPositionBorsht.setFoodCategory(soupFoodCategory);
        
        MenuPosition menuPositionShi = new MenuPosition("Щи");
        menuPositionShi.setDescription("Щи");
        menuPositionShi.setImageUrl("https://cdn.lifehacker.ru/wp-content/uploads/2015/11/shutterstock_310946489_1446296212-1140x570.jpg");
        menuPositionShi.setId(42L);
        for (Topping soupTopping : createSoupTopping) {
            menuPositionShi.addTopping(soupTopping);
        }
        menuPositionShi.setFoodCategory(soupFoodCategory);
    
        MenuPosition menuPositionQuadroFormaggi = new MenuPosition("Пицца 4 сыра");
        menuPositionQuadroFormaggi.setImageUrl("https://pizzaman.ru/image/cache/data/pizzy/4%20сыра-760x440.png");
        menuPositionQuadroFormaggi.setId(43L);
        menuPositionQuadroFormaggi.setPrice(250.0f);
        List<FoodSize> createPizzaFoodSizes = createPizzaFoodSizes();
        for (FoodSize foodSize : createPizzaFoodSizes) {
            menuPositionQuadroFormaggi.addFoodSize(foodSize);
        }
        List<Topping> createPizzaTopping = createPizzaTopping();
        for(Topping topping: createPizzaTopping){
            menuPositionQuadroFormaggi.addTopping(topping);
        }
        menuPositionQuadroFormaggi.setFoodCategory(pizzaFoodCategory);
        
        menu.addMenuPosition(menuPositionBorsht);
        menu.addMenuPosition(menuPositionShi);
        menu.addMenuPosition(menuPositionQuadroFormaggi);
        return menu;
    }

}
