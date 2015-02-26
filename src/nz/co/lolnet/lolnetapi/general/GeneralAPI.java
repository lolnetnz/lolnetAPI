package nz.co.lolnet.lolnetapi.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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
public class GeneralAPI {

    /**
     * Checks if the Mojang Session Servers are online.
     *
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

    /**
     * Grabs the UUID of a Player.
     *
     * @param playerName
     * @return String Representation of a UUID, which you can then use
     * UUID.fromString() on.
     * @throws IOException
     * @throws ParseException
     */
    public static String getStringUUID(String playerName) throws IOException, ParseException {

        String player = playerName.toLowerCase();

        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(player, "UTF-8");
        data += "&" + URLEncoder.encode("currenttime", "UTF-8") + "=" + URLEncoder.encode(System.currentTimeMillis() + "", "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/getplayeruuid.php");
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

        String rawUUID = (String) json.get("id");
        String UUID = rawUUID.substring(0, 8) + "-" + rawUUID.substring(8, 12) + "-" + rawUUID.substring(12, 16) + "-" + rawUUID.substring(16, 20) + "-" + rawUUID.substring(20, 32);

        return UUID;
    }

    /**
     * Gets your public IP Address.
     * @return A String representation of your IP Address.
     * @throws IOException
     * @throws ParseException 
     */
    public static String getIP() throws IOException, ParseException {
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/getmyip.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(lolnetAPI.httpTimeOut);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output = rd.readLine();
        rd.close();
        return output;
    }
    
    /**
     * Gets the players lolnet Forum ID given the UUID of the player.
     * 
     * @param UUID
     * @return long Player Forum ID, will return 0 if no ID is found in the database.
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    public static long getPlayerForumIDFromUUID(String UUID) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        String data = URLEncoder.encode("uuid", "UTF-8") + "=" + URLEncoder.encode(UUID, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/getPlayerForumIDFromUUID.php");
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
        
        return (long) json.get("user_id");
    }
    
    /**
     * Gets the players lolnet Forum ID given the Username of the player.
     * 
     * @param playername
     * @return long Player Forum ID, will return 0 if no ID is found in the database.
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    public static long getPlayerForumIDFromUsername(String playername) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/general/getPlayerForumIDFromUsername.php");
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
        
        return (long) json.get("user_id");
    }

}
