/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.http.jetty;

import com.four_friends.demetraserver.entity.Entity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gekko
 */
public class HttpHelper {

    public static void answerError(HttpServletResponse response, String exceptionMessage) throws UnsupportedEncodingException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "error");
        jsonObject.addProperty("message", exceptionMessage);
        jsonObject.add("data", new JsonObject());
        String reply = jsonObject.getAsString();
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static void answerError(HttpServletResponse response, Exception ex) throws UnsupportedEncodingException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "error");
        jsonObject.addProperty("message", ex.getMessage());
        jsonObject.add("data", new JsonObject());
        String reply = jsonObject.getAsString();
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static void answerEntity(HttpServletResponse response, Entity entity) throws UnsupportedEncodingException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "error");
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(entity);
        jsonObject.add("data", jsonElement);
        String reply = jsonObject.getAsString();
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public static void answerEntities(HttpServletResponse response, List entities) throws UnsupportedEncodingException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "error");
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(entities);
        jsonObject.add("data", jsonElement);
        String reply = jsonObject.toString();
        response.getOutputStream().write(reply.getBytes("UTF-8"));
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
