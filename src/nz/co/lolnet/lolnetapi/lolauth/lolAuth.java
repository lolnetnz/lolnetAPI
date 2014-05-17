package nz.co.lolnet.lolnetapi.lolauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolAuth {

    public static boolean login(String playerName, String password) throws IOException, ParseException {
        PhpHandler p = new PhpHandler();

        String player = playerName.toLowerCase();
        String passwordHash = p.phpbb_hash(password);

        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(player, "UTF-8");
        data += "&" + URLEncoder.encode("passwordhash", "UTF-8") + "=" + URLEncoder.encode(passwordHash, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/login.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        wr.close();
        rd.close();
        return "true".equals(json.get("loggedin").toString());
    }

}
