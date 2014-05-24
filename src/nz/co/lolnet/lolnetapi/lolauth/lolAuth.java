package nz.co.lolnet.lolnetapi.lolauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import nz.co.lolnet.lolnetAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolAuth {

    /**
     * Attempts to login a player against the lolnet database
     * 
     * @version 1.0 17/05/2014 - First Added
     * @version 2.0 24/05/2014 - Comparison with hash rather than checking directly against database
     * @param authHash
     * @param playerName
     * @param password
     * @return true if login was successful
     * @throws IOException
     * @throws ParseException 
     */
    public static boolean login(String authHash, String playerName, String password) throws IOException, ParseException {
        PhpbbHandler p = new PhpbbHandler();

        String player = playerName.toLowerCase();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(player, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/gethash.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(lolnetAPI.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        wr.close();
        rd.close();
        
        String hash = (String) json.get("hash");
        
        return p.phpbb_check_hash(password, hash);
    }

}
