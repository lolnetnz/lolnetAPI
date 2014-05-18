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
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolCon {

    public static HashMap<String, Long> getTop10MonthlyVoters() throws IOException, ParseException {
        HashMap<String, Long> output = new HashMap<>();

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/gettop10monthlyvotes.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(10000);

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        rd.close();

        Iterator<?> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String username = (String) keys.next();
            long votenum = (long) json.get(username);
            output.put(username, votenum);
        }

        return output;
    }

    public static HashMap<String, Integer> getForumGroups(String authHash) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        HashMap<String, Integer> output = new HashMap<>();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumgroups.php");
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

        Iterator<?> keys = json.keySet().iterator();

        while (keys.hasNext()) {
            String group_id = (String) keys.next();
            String group_name = (String) json.get(group_id);
            output.put(group_name, Integer.parseInt(group_id));
        }

        return output;
    }

    public static int getForumUserID(String authHash, String username) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumuserid.php");
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

        Iterator<?> keys = json.keySet().iterator();

        while (keys.hasNext()) {
            String user = (String) keys.next();
            long forumid = (long) json.get(user);
            return safeLongToInt(forumid);
        }
        throw new NoSuchObjectException("Username not found in Database!");       
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

}
