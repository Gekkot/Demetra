/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.four_friends.demetraserver.dao;

import com.four_friends.demetraserver.db.data_provider.exception.OwnerNotFoundException;
import com.four_friends.demetraserver.db.sqlite.SqliteDBConnect;
import com.four_friends.demetraserver.entity.Owner;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
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
public class OwnerDaoTest {

    public OwnerDaoTest() {
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

    /**
     * Test of createOwner method, of class OwnerDao.
     */
    @Test
    public void testCreateOwner() throws Exception {
        SqliteDBConnect sqliteDBConnect = new SqliteDBConnect();
        sqliteDBConnect.createDB();
        ConnectionSource connectionSource = sqliteDBConnect.createConnectionSource();
        OwnerDao ownerDao = new OwnerDao(connectionSource);

        Owner owner = new Owner();
        owner.setId(101);
        owner.setOwnerName("test owner");
        owner.setOwnerRestarauntName("teste teste");
        owner.setUrl("http://example.com");

        Owner createOwner = ownerDao.createOwner(owner);
        assertEquals("test owner", owner.getOwnerName());
        assertEquals("teste teste", owner.getOwnerRestarauntName());

        List<Owner> all = ownerDao.getAll();
        assertTrue(all.size() > 0);

    }

    @Test
    public void testGetAll() throws SQLException, OwnerNotFoundException {
        SqliteDBConnect sqliteDBConnect = new SqliteDBConnect();
        sqliteDBConnect.createDB();
        ConnectionSource connectionSource = sqliteDBConnect.createConnectionSource();
        OwnerDao ownerDao = new OwnerDao(connectionSource);

        
        Owner owner = new Owner();
        owner.setId(101);
        owner.setOwnerName("test owner");
        owner.setOwnerRestarauntName("teste teste");
        owner.setUrl("http://example.com");
        
        ownerDao.createOwner(owner);
        
        List<Owner> all = ownerDao.getAll();
        assertTrue(all.size() > 0);
        System.out.println("all size: " + all.size());
    }

}
