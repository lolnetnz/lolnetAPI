package nz.co.lolnet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import nz.co.lolnet.lolnetapi.lolauth.lolAuth;
import nz.co.lolnet.lolnetapi.lolcon.lolCon;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolnetAPI {
    
    public static final int httpTimeOut = 10000;
    private static final String authHash = "abc123";
    
    public static void main(String[] args) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {  
        int forumUserID = lolCon.getForumUserID(authHash, "james137137");
        System.out.println(forumUserID);
        HashMap<String, Integer> forumGroups = lolCon.getForumGroups(authHash);
        
        HashMap<Integer,String> forumGroupsID = swapHashMap(forumGroups);
        ArrayList<Integer> forumUserForumGroups = lolCon.getForumUserForumGroups(authHash, forumUserID);
        for (Integer integer : forumUserForumGroups) {
            System.out.println(forumGroupsID.get(integer));
        }
    }

    private static HashMap<Integer, String> swapHashMap(HashMap<String, Integer> forumGroups) {
        HashMap<Integer,String> forumGroupsID = new HashMap<>();
        for (String string : forumGroups.keySet()) {
            forumGroupsID.put(forumGroups.get(string), string);
        }
        return forumGroupsID;
    }
    
}
