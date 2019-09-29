/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.Owner;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author gekko
 */
public class OwnerGenerator {


    public static long getRandomId() {
        return randomLongWithRange(0, Long.MAX_VALUE);
    }

    private static long randomLongWithRange(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public static Owner getKFCOwner() {
        Owner kfcOwner = new Owner();
        kfcOwner.setOwnerName("KFC");
        kfcOwner.setOwnerRestarauntName("KFC");
        kfcOwner.setUrl("http://kfc.ru");
        kfcOwner.setLogoUrl("https://lh3.googleusercontent.com/7t2VkqpzA_J9t6Lr8okYwtIuKmO0S5FlsxMCS7z3GdN-0WNFAOjk1xKweaPbkFRbVmI=s180");
        return kfcOwner;
    }

    public static Owner getBKOwner() {
        Owner bkOwner = new Owner();
        bkOwner.setOwnerName("Burger King");
        bkOwner.setOwnerRestarauntName("Burger King");
        bkOwner.setUrl("http://burgerking.ru");
        bkOwner.setLogoUrl("https://lh3.googleusercontent.com/1v-Ay1AmsukO2sCByosCdvr3061uG8UKUfpzlPxO8Xi1TPSnVVyBkA90cqiRgxa6kdM=s180");
        return bkOwner;
    }
    
    public static Owner getBlackStarBurgerOwner() {
        Owner bsOwner = new Owner();
        bsOwner.setOwnerName("BlackStar");
        bsOwner.setOwnerRestarauntName("BlackStar Burger");
        bsOwner.setUrl("https://blackstarburger.ru/");
        bsOwner.setLogoUrl("https://blackstarburger.ru/local/templates/bsb/static/image/bsb-logo.png");
        return bsOwner;
    }

    public static Owner getOkolitcaOwner() {
        Owner okolitcaOwner = new Owner();
        okolitcaOwner = new Owner();
        okolitcaOwner.setOwnerName("Околица");
        okolitcaOwner.setOwnerRestarauntName("Околица");
        okolitcaOwner.setUrl("");
        return okolitcaOwner;
    }
    
    public static Owner getEurasiaOwner(){
        Owner eurasiaOwner = new Owner();
        eurasiaOwner.setOwnerName("Евразия");
        eurasiaOwner.setOwnerRestarauntName("Евразия");
        eurasiaOwner.setUrl("");
        eurasiaOwner.setLogoUrl("https://p1.zoon.ru/preview/V9XYqb3oENIwG65gZg7pjg/604x376x85/0/7/3/514c0ed5a0f302702d000017_514c13cfb001c.jpg");
        return eurasiaOwner;
        
    }

    public static Owner[] getOwners() {
        return new Owner[]{
            getKFCOwner(), getBKOwner(), getOkolitcaOwner()
        };
    }
    
    public static Map<Long, Owner> getOwnersMap(){
        return Stream.of(getOwners()).collect(Collectors.toMap(Owner::getId, p->p));
    }

}
