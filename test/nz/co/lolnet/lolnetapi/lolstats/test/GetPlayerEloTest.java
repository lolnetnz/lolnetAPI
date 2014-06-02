package nz.co.lolnet.lolnetapi.lolstats.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.lolnet.lolnetapi.lolstats.lolStats;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cptwin
 */
public class GetPlayerEloTest {

    @Test
    public void playerInDB()
    {
        try {
            double d = lolStats.getPlayerElo("cptwin");
            assertNotNull(d);
        } catch (IOException | ParseException | NullPointerException ex) {
            Logger.getLogger(GetPlayerEloTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void playerNotInDB()
    {
        try {
            double d2 = lolStats.getPlayerElo("cptwinz");
        } catch (IOException | ParseException | NullPointerException ex) {
            Logger.getLogger(GetPlayerEloTest.class.getName()).log(Level.SEVERE, null, ex);
            assert true;
        }
    }
    
}
