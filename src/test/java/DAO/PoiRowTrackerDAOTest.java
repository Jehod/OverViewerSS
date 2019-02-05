/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.SimpleRowTracker;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nrochas
 */
public class PoiRowTrackerDAOTest {
    
    public PoiRowTrackerDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAllRowTracker method, of class PoiRowTrackerDAO.
     */
    @org.junit.Test
    public void testFindAllRowTracker() {
        System.out.println("findAllRowTracker");
        PoiRowTrackerDAO instance = new PoiRowTrackerDAO("");
        List<SimpleRowTracker> expResult = null;
        List<SimpleRowTracker> result = instance.findAllRowTracker();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
