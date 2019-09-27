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
        return kfcOwner;
    }

    public static Owner getBKOwner() {
        Owner bkOwner = new Owner();
        bkOwner.setOwnerName("Burger King");
        bkOwner.setOwnerRestarauntName("Burger King");
        bkOwner.setUrl("http://burgerking.ru");
        return bkOwner;
    }
    
    public static Owner getBlackStarBurgerOwner() {
        Owner bsOwner = new Owner();
        bsOwner.setOwnerName("BlackStar");
        bsOwner.setOwnerRestarauntName("BlackStar Burger");
        bsOwner.setUrl("https://blackstarburger.ru/");
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

    public static Owner[] getOwners() {
        return new Owner[]{
            getKFCOwner(), getBKOwner(), getOkolitcaOwner()
        };
    }
    
    public static Map<Long, Owner> getOwnersMap(){
        return Stream.of(getOwners()).collect(Collectors.toMap(Owner::getId, p->p));
    }

}
