/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.FoodTag;


/**
 *
 * @author Konstantin Sorokin
 */
public class FoodTagGenerator {
    
    public static FoodTag createFastFoodTag() {
        return new FoodTag("Fast food");
    }

    public static FoodTag createAsianTag() {
        return new FoodTag("Азиатская кухня");
    }

    public static FoodTag createItalianTag() {
        return new FoodTag("Итальянская кухня");
    }

    public static FoodTag createBisnessLanchTag() {
        return new FoodTag("Бизнес-ланч");
    }

    public static FoodTag createBurgerTag() {
        return new FoodTag("Бургеры");
    }

    public static FoodTag createVegeterianTag() {
        return new FoodTag("Вегетарианское");
    }
    
    public static FoodTag createRussianTag() {
        return new FoodTag("Русская кухня");
    }

    public static FoodTag createShawarmaTag() {
        return new FoodTag("Шаверма");
    }
    
    
}
