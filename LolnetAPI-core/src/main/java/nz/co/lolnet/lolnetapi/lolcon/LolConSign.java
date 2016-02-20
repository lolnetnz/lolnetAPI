package nz.co.lolnet.lolnetapi.lolcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import nz.co.lolnet.lolnetapi.settings.Settings;

/**
 *
 * @author James
 */
public class LolConSign {
    
    public static HashMap<String,Object> CustomItems = new HashMap<>();
    
    public static void addCustomItem(String name, Object object)
    {
        CustomItems.put(name, object);
    }
    
    public static void removeCustomItem(String name)
    {
        CustomItems.remove(name);
    }
    
    
    //29/05/2014 - CptWin - Added throwing exceptions and return statement
    public static boolean logSignTransaction(String authHash,String userName, String signType, String serverName, String location, String signDetail, String Cost) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {

            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(Settings.checkAPIKey(authHash), "UTF-8");
            data += "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(signType, "UTF-8");
            data += "&" + URLEncoder.encode("server", "UTF-8") + "=" + URLEncoder.encode(serverName, "UTF-8");
            data += "&" + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
            data += "&" + URLEncoder.encode("details", "UTF-8") + "=" + URLEncoder.encode(signDetail, "UTF-8");
            data += "&" + URLEncoder.encode("cost", "UTF-8") + "=" + URLEncoder.encode(Cost, "UTF-8");
            
            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/logsigntransaction.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(Settings.httpTimeOut);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
            wr.close();
            rd.close();
            
            return (boolean) json.get("success");
    }
}
