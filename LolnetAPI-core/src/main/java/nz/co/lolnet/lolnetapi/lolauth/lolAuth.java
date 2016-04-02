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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import nz.co.lolnet.lolnetapi.APIKeyNotSetException;
import nz.co.lolnet.lolnetapi.settings.Settings;

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
     * @version 1.0 26/01/2015 - First Added
     * @version 3.0 26/01/2015 - Changed to login with UUID
     * @param authHash
     * @param playername
     * @param UUID
     * @param password
     * @return true if login was successful
     * @throws IOException
     * @throws ParseException
     */
    public static boolean login(String authHash, String playername, String UUID, String password) throws IOException, ParseException {
        String version = "2";
        try {
            authHash = Settings.checkAPIKey(authHash);
        } catch (APIKeyNotSetException ex) {
            Logger.getLogger(lolAuth.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
        data += "&" + URLEncoder.encode("uuid", "UTF-8") + "=" + URLEncoder.encode(UUID, "UTF-8");
        data += "&" + URLEncoder.encode("version", "UTF-8") + "=" + URLEncoder.encode(version, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername.toLowerCase(), "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/checkpassword.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        wr.close();
        rd.close();

        boolean result = (Boolean) json.get("result");

        return result;
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

        /*String player = playerName.toLowerCase();

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
        
         boolean result = (Boolean) json.get("success");*/
        //return result; //This method has been deprecated
        return false;
    }

    /**
     * Registers a new player to the database.
     *
     * @param authHash
     * @param playerName
     * @param UUID
     * @param email
     * @param ip
     * @param password
     * @return true If registration was successful
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException
     */
    public static boolean register(String authHash, String playerName, String UUID, String email, String ip, String password) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        try {
            authHash = Settings.checkAPIKey(authHash);
        } catch (APIKeyNotSetException ex) {
            Logger.getLogger(lolAuth.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
         data += "&" + URLEncoder.encode("uuid", "UTF-8") + "=" + URLEncoder.encode(UUID, "UTF-8");
        
         URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/register.php");
         URLConnection conn = url.openConnection();
         conn.setDoOutput(true);
         conn.setConnectTimeout(Settings.httpTimeOut);
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
    public static boolean isUsernameAlreadyRegistered(String playerName) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isusernamealreadyregistered.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
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
     * Checks if the email address is already registered to an account in the
     * database.
     *
     * @version 1.0 26/01/2015 - Javadoc First Added
     * @param email
     * @return true If email is already registered to an account
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException
     */
    public static boolean isEmailAlreadyRegistered(String email) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isemailalreadyregistered.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
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
     * Checks if the UUID is already registered to a player in the database.
     *
     * @version 1.0 26/01/2015 - Initial Version
     * @param UUID
     * @return true if UUID is already registered
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException
     */
    public static boolean isUUIDAlreadyRegistered(String UUID) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("uuid", "UTF-8") + "=" + URLEncoder.encode(UUID, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isuuidalreadyregistered.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
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
     * Checks if the player on the server matches the data stored in the lolnet database.
     * @param playername
     * @param UUID
     * @return true if the player data matches
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    public static boolean doesStoredUsernameandUUIDMatch(String playername, String UUID) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException
    {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");
        data += "&" + URLEncoder.encode("uuid", "UTF-8") + "=" + URLEncoder.encode(UUID, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolauth/isUsernameAndUUIDMatch.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (boolean) json.get("match");
    }

    /**
     * Tidies up the IP address that is output from the Minecraft server.
     * Usually in the form 127.0.0.1:25565
     *
     * @param ip
     * @return String cleaned ip address, eg: 127.0.0.1
     */
    private static String cleanIpAddress(String ip) {
        String output = ip;
        output = output.replaceAll("/", "");
        String[] str_array = output.split(":");
        output = str_array[0];
        return output;
    }

    /**
     * Creates the CRC32 hash of the email address for use in the PhpBB forum
     * database.
     *
     * @param email
     * @return long CRC32 Hash of the email address
     */
    private static long emailHash(String email) {
        CRC32 crcMaker = new CRC32();
        crcMaker.update(email.getBytes());
        long crc = crcMaker.getValue();
        return crc + email.length();
    }

}
