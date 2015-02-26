/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi.general;

import java.io.IOException;
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
 * @author Dajne Win
 */
public class GeneralAPITest {
    
    public GeneralAPITest() {
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
    public void getPlayerForumIDFromUUIDPlayerNotInDatabase()
    {
        try {
            assertEquals(0, GeneralAPI.getPlayerForumIDFromUUID("00012345000"));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(GeneralAPITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getPlayerForumIDFromUUIDCptWin()
    {
        try {
            assertEquals(2, GeneralAPI.getPlayerForumIDFromUUID("e795cc83-31b2-4b7e-bbbf-012089a1a59b"));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(GeneralAPITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
