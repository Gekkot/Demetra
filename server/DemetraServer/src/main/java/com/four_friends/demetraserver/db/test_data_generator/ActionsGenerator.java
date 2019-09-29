/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.db.test_data_generator;

import com.four_friends.demetraserver.entity.Actions;

/**
 *
 * @author gekko
 */
public class ActionsGenerator {
    public static Actions createActions(){
            Actions actions  = new Actions();
            actions.setShortDescription("Распродажа бургеров");
            actions.setStartDate("25.09.2019");
            actions.setEndDate("30.09.2019");
            return actions;
    }
}
