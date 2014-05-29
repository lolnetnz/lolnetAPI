/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.co.lolnet.lolnetapi.lolcon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author James
 */
public class LolConSign {
    
    public static boolean logSignTransaction(String authHash,String userName, String signType, String serverName, String location, String signDetail, String Cost) {
        boolean result = false;
        try {
            // Construct data

            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            data += "&" + URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
            data += "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(signType, "UTF-8");
            data += "&" + URLEncoder.encode("server", "UTF-8") + "=" + URLEncoder.encode(serverName, "UTF-8");
            data += "&" + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
            data += "&" + URLEncoder.encode("details", "UTF-8") + "=" + URLEncoder.encode(signDetail, "UTF-8");
            data += "&" + URLEncoder.encode("cost", "UTF-8") + "=" + URLEncoder.encode(Cost, "UTF-8");
            // Send data
            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/logsigntransaction.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
            result = (boolean) json.get("success");
            
            
            wr.close();
            rd.close();
        }
        catch (Exception e) {
        }
        return result;
    }
}
