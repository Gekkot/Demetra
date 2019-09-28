/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.entity.food_params;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gekko
 */
public class MenuAnswerTest {
    
    public MenuAnswerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        Menu menu = new Menu();
        
        FoodCategory foodCategorySoup = new FoodCategory("Супы");
        FoodCategory foodCategoryHot = new FoodCategory("Горячее");
        FoodCategory foodCategoryDesert = new FoodCategory("Десерт");
        
        MenuPosition menuPositionBorsht = new MenuPosition("Борщ");
        menuPositionBorsht.setDescription("Борщ");
        menuPositionBorsht.setFoodCategory(foodCategorySoup);
        
        MenuPosition menuPositionShi = new MenuPosition("Щи");
        menuPositionShi.setDescription("Щи");
        menuPositionShi.setFoodCategory(foodCategorySoup);
     
        menu.addMenuPosition(menuPositionBorsht);
        menu.addMenuPosition(menuPositionShi);
        
        MenuAnswer menuAnswer = new MenuAnswer();
        JsonElement menusonObject = menuAnswer.createJsonObject(menu);
        Gson gson = new Gson();
        //JsonElement toJsonTree = gson.toJsonTree(menu);
        String toString = menusonObject.toString();
        System.out.println(toString);
        
    }
    
}
