/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi;

import nz.co.lolnet.lolnetapi.settings.Settings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class APIKeyNotSetExceptionTest {
    
    public APIKeyNotSetExceptionTest() {
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
    
    @Test(expected = APIKeyNotSetException.class)
    public void testExceptionIsThrown() throws APIKeyNotSetException {
        Settings.checkAPIKey(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
