package nz.co.lolnet.lolnetapi.lolchat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import nz.co.lolnet.lolnetapi.settings.Settings;

/**
 *
 * @author cptwin
 */
public class lolChat {
    
    public static void recordMessage(String authHash, String servername, String playername, String message) throws UnsupportedEncodingException, MalformedURLException, IOException
    {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(Settings.checkAPIKey(authHash), "UTF-8");
        data += "&" + URLEncoder.encode("servername", "UTF-8") + "=" + URLEncoder.encode(servername, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");
        data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolchat/recordmessage.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
    }
    
}
