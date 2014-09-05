package nz.co.lolnet.lolnetapi.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import nz.co.lolnet.lolnetAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class GeneralAPI {
    
    /**
     * Checks if the Mojang Session Servers are online.
     * @return true if the Session Servers are online
     * @throws IOException
     * @throws ParseException 
     */
    public static boolean isMojangOnline() throws IOException, ParseException {
        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/ismojangonline.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(lolnetAPI.httpTimeOut);
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        rd.close();       
        String status = (String) json.get("session.minecraft.net");       
        return status.equalsIgnoreCase("green");
    }
    
}
