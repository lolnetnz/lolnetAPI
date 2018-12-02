/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi.osticket;

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
import nz.co.lolnet.lolnetapi.settings.Settings;
import org.json.simple.parser.ParseException;

/**
 *
 * @author James
 */
public class main {

    public static void main(String[] args) {
        try {
            test("377C4ACB67C267CA82D56EDFE81D87C4");
        } catch (MalformedURLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean test(String authHash) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {

        boolean result = false;
        try {
            // Construct data

            String data = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
            // Send data
            URL url = new URL("https://www.lolnet.co.nz/ticket/api/http.php/tickets.json");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(Settings.connectTimeout);
            conn.setReadTimeout(Settings.readTimeout);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }
        return result;
    }

}
