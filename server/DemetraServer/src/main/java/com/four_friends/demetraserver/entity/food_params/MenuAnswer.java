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
    
    List<MenuPosition> getMenuPositionsOfCategory(List<MenuPosition> menuPositions,FoodCategory foodCategory){
        return menuPositions.stream().filter((t) -> {
            return t.getFoodCategory().getId() == foodCategory.getId();
        }).collect(Collectors.toList());
    }
    
    private JsonObject createCategoryToJson(FoodCategory foodCategory,List<MenuPosition> menuPositions){
         Gson gson = new Gson();
         JsonObject categoryJsonObject = new JsonObject();
         categoryJsonObject.addProperty("name", foodCategory.name);
         categoryJsonObject.addProperty("description", foodCategory.description);
         categoryJsonObject.addProperty("id", foodCategory.getId());
         List<MenuPosition> menuPositionsOfCategory = getMenuPositionsOfCategory(menuPositions, foodCategory);
         JsonArray jsonArray = new JsonArray();
         for(MenuPosition menuPosition: menuPositionsOfCategory){
             JsonObject jsonObject = new JsonObject();
             jsonObject.addProperty("name", menuPosition.getName());
             jsonObject.addProperty("description", menuPosition.getDescription());
             jsonObject.addProperty("price", menuPosition.getPrice());
             jsonObject.addProperty("id", menuPosition.getId());
             jsonArray.add(jsonObject);
         }
         categoryJsonObject.getAsJsonObject().add("menuPositions", jsonArray);
         return categoryJsonObject.getAsJsonObject();
    }
    
    JsonElement createJsonObject(Menu menu){
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
