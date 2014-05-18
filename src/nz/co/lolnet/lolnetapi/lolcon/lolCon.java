package nz.co.lolnet.lolnetapi.lolcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolCon {
    
    public static HashMap<String, Long> getTop10MonthlyVoters() throws IOException, ParseException
    {
        HashMap<String, Long> output = new HashMap<>();
        
        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/gettop10monthlyvotes.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(10000);

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        rd.close();
        
        Iterator<?> keys = json.keySet().iterator();
        while(keys.hasNext()) {
            String username = (String) keys.next();
            long votenum = (long) json.get(username);
            output.put(username, votenum);
        }
        
        return output;        
    }
    
}
