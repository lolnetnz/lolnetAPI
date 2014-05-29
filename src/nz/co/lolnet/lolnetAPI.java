package nz.co.lolnet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolnetAPI {
    
    public static final int httpTimeOut = 10000;
    //private static final String authHash = "abc123";
    
    public static void main(String[] args) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        
        /*
        int forumUserID = lolCon.getForumUserID(authHash, "james137137");
        System.out.println(forumUserID);
        HashMap<String, Integer> forumGroups = lolCon.getForumGroups(authHash);
        
        HashMap<Integer,String> forumGroupsID = swapHashMap(forumGroups);
        ArrayList<Integer> forumUserForumGroups = lolCon.getForumUserForumGroups(authHash, forumUserID);
        for (Integer integer : forumUserForumGroups) {
            System.out.println(forumGroupsID.get(integer));
        }
                */
        //This stuff needs to go into unit tests
    }    
}
