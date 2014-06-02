package nz.co.lolnet.lolnetapi.lolstats;

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
public class lolStats {

    public static void logPlayerIP(String authHash, String playername, String ipaddress) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");
        data += "&" + URLEncoder.encode("ipaddress", "UTF-8") + "=" + URLEncoder.encode(ipaddress, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolstats/logplayerip.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(lolnetAPI.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
    }

    public static void logPlayerFood(String authHash, String playername, String servername, String foodname) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");
        data += "&" + URLEncoder.encode("servername", "UTF-8") + "=" + URLEncoder.encode(servername, "UTF-8");
        data += "&" + URLEncoder.encode("foodname", "UTF-8") + "=" + URLEncoder.encode(foodname, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolstats/logplayerfood.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(lolnetAPI.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
    }

    public static double getPlayerElo(String playername) throws UnsupportedEncodingException, IOException, ParseException, NullPointerException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode("cptwin", "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolstats/getplayerelo.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (double) json.get("elo");
    }

}
