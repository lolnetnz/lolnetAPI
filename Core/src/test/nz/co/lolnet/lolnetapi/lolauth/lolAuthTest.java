/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi.lolauth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CptWin
 */
public class lolAuthTest {
    
    public lolAuthTest() {
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
    
    @Test
    public void doesStoredUsernameandUUIDMatchFakeUsername()
    {
        try {
            String thisUsernameShouldNotBeInDatabase = "unknownusername123";
            String fakeUUID = "00001234560000";
            assertEquals(false, lolAuth.doesStoredUsernameandUUIDMatch(thisUsernameShouldNotBeInDatabase, fakeUUID));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(lolAuthTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void doesStoredUsernameandUUIDMatchRealUsernameFakeUUID()
    {
        try {
            String thisUsernameShouldNotBeInDatabase = "cptwin";
            String fakeUUID = "00001234560000";
            assertEquals(false, lolAuth.doesStoredUsernameandUUIDMatch(thisUsernameShouldNotBeInDatabase, fakeUUID));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(lolAuthTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void doesStoredUsernameandUUIDMatchFakeUsernameRealUUID()
    {
        try {
            String thisUsernameShouldNotBeInDatabase = "unknownusername123";
            String fakeUUID = "e795cc83-31b2-4b7e-bbbf-012089a1a59b";
            assertEquals(false, lolAuth.doesStoredUsernameandUUIDMatch(thisUsernameShouldNotBeInDatabase, fakeUUID));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(lolAuthTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void doesStoredUsernameandUUIDMatchRealUsernameRealUUID()
    {
        try {
            String thisUsernameShouldNotBeInDatabase = "CptWin";
            String fakeUUID = "e795cc83-31b2-4b7e-bbbf-012089a1a59b";
            assertEquals(true, lolAuth.doesStoredUsernameandUUIDMatch(thisUsernameShouldNotBeInDatabase, fakeUUID));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(lolAuthTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
