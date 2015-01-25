package nz.co.lolnet.lolnetapi.lolauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.CRC32;
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
     * @version 2.1 26/01/2015 - This version is now deprecated due to UUID changes
     * @param authHash
     * @param playerName
     * @param password
     * @return true if login was successful
     * @throws IOException
     * @throws ParseException 
     * @deprecated 
     */
    public static boolean login(String authHash, String playerName, String password) throws IOException, ParseException {

        String player = playerName.toLowerCase();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(player, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/checkpassword.php");
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
        
        boolean result = (Boolean) json.get("result");
        
        //return result; //This method has been deprecated
        return false;
    }
    
    /**
     * REQUIRES JAVADOC'ING
     * 
     * @param authHash
     * @param playerName
     * @param password
     * @return
     * @throws IOException
     * @throws ParseException 
     * @deprecated
     */
    public static boolean changePassword(String authHash, String playerName, String password) throws IOException, ParseException {

        String player = playerName.toLowerCase();

        PhpbbHandler phpbbhandler = new PhpbbHandler();
        String passwordHash = phpbbhandler.phpbb_hash(password);
        password = passwordHash;
        
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(player, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/changepassword.php");
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
        
        boolean result = (Boolean) json.get("success");
        
        return result;
    }
    
    /**
     * Registers a new player to the database.
     * 
     * @param authHash
     * @param playerName
     * @param email
     * @param ip
     * @param password
     * @return true If registration was successful
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     * @deprecated 
     */
    public static boolean register(String authHash, String playerName, String email, String ip, String password) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        PhpbbHandler phpbbhandler = new PhpbbHandler();
        
        //Prepare all data needed to register
        String passwordHash = phpbbhandler.phpbb_hash(password);
        long timestamp = System.currentTimeMillis() / 1000;
        String playerIP = cleanIpAddress(ip);
        long emailHash = emailHash(email);
        
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");
        data += "&" + URLEncoder.encode("player_password", "UTF-8") + "=" + URLEncoder.encode(passwordHash, "UTF-8");
        data += "&" + URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
        data += "&" + URLEncoder.encode("timestamp", "UTF-8") + "=" + URLEncoder.encode(timestamp + "", "UTF-8");
        data += "&" + URLEncoder.encode("player_ip", "UTF-8") + "=" + URLEncoder.encode(playerIP, "UTF-8");
        data += "&" + URLEncoder.encode("email_hash", "UTF-8") + "=" + URLEncoder.encode(emailHash + "", "UTF-8");
        
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/register.php");
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
        
        return (boolean) json.get("success");
    }
    
    /**
     * Checks if the username is already registered in the database.
     * 
     * @version 1.0 26/01/2015 - Javadoc First Added
     * @param playerName
     * @return true if the player already exists in the database
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    public static boolean isUsernameAlreadyRegistered(String playerName) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");
        
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isusernamealreadyregistered.php");
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
        
        return (boolean) json.get("registered");
    }
    
    /**
     * Checks if the email address is already registered to an account in the database.
     * 
     * @version 1.0 26/01/2015 - Javadoc First Added
     * @param email
     * @return true If email is already registered to an account
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    public static boolean isEmailAlreadyRegistered(String email) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
        
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isemailalreadyregistered.php");
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
        
        return (boolean) json.get("registered");
    }
    
    public static boolean isUUIDAlreadyRegistered(String UUID)
    {
        return true;
    }
    
    private static String cleanIpAddress(String ip) {
        String output = ip;
        output = output.replaceAll("/", "");
        String[] str_array = output.split(":");
        output = str_array[0];
        return output;
    }
    
    private static long emailHash(String email) {
        CRC32 crcMaker = new CRC32();
        crcMaker.update(email.getBytes());
        long crc = crcMaker.getValue();
        return crc + email.length();
    }

}
