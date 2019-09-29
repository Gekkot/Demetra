/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.entity.food_params;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO: move to convert package;
 * @author gekko
 */
public class MenuAnswer {
    Long restarauntId;
    List<FoodCategory> foodCategoryList = new ArrayList<>();
    
    public static List<MenuPosition> getMenuPositionsOfCategory(List<MenuPosition> menuPositions,FoodCategory foodCategory){
        return menuPositions.stream().filter((t) -> {
            return t.getFoodCategory().getId() == foodCategory.getId();
        }).collect(Collectors.toList());
    }
    
    private static JsonObject createCategoryToJson(FoodCategory foodCategory, List<MenuPosition> menuPositions) {
        Gson gson = new Gson();
        JsonObject categoryJsonObject = new Gson().toJsonTree(foodCategory).getAsJsonObject();
        List<MenuPosition> menuPositionsOfCategory = getMenuPositionsOfCategory(menuPositions, foodCategory);
        JsonArray jsonArray = new JsonArray();
        for (MenuPosition menuPosition : menuPositionsOfCategory) {
            JsonObject jsonObject = gson.toJsonTree(menuPosition).getAsJsonObject();
            jsonObject.remove("foodCategory");
             jsonArray.add(jsonObject);
         }
         categoryJsonObject.getAsJsonObject().add("menuPositions", jsonArray);
         return categoryJsonObject.getAsJsonObject();
    }
    
    public static JsonElement createJsonObject(Menu menu){
        Set<FoodCategory> foodCategories = menu.getFoodCategory();
        List<MenuPosition> menuPositions = menu.getMenuPositions();
        
        
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(FoodCategory foodCat: foodCategories){
            JsonObject createCategoryToJson = createCategoryToJson(foodCat, menuPositions);
            jsonArray.add(createCategoryToJson);
        }
        return jsonArray;
        
        
    }
    
}
