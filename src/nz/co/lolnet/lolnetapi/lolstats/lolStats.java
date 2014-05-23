package nz.co.lolnet.lolnetapi.lolstats;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import nz.co.lolnet.lolnetAPI;

/**
 *
 * @author cptwin
 */
public class lolStats {
    
    public static void logPlayerIP(String authHash, String playername, String ipaddress) throws UnsupportedEncodingException, MalformedURLException, IOException
    {
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
    
}
