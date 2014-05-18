package nz.co.lolnet.lolAuth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.lolnet.lolnetapi.lolauth.lolAuth;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cptwin
 */
public class loginTest {
    
    public loginTest() {
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
    public void badLogin() {      
        try {
            assertEquals(lolAuth.login("nooblet72", "abcdefg"), false);
        } catch (IOException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void goodLogin1() {
        try {
            assertEquals(lolAuth.login("Nooblet72", "123456"), true);
        } catch (IOException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void goodLogin2() {
        try {
            assertEquals(lolAuth.login("noobly72", "654321"), true);
        } catch (IOException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void unknownUsername() {
        try {
            assertEquals(lolAuth.login("ouhofo87878787", "123456"), false);
        } catch (IOException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
