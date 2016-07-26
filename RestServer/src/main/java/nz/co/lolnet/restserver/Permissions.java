/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.restserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author James
 */
public class Permissions {

    public static boolean checkToken(String token, LolnetAPI api) {
        if (api.getRequiredPermission() == null) {
            return true;
        }
        
        String decrypt = Encryptor.decrypt(Main.getKey(), "RandomInitVector", token);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(decrypt);
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.get("EVERYTHING") != null && jSONObject.get("EVERYTHING") == Boolean.TRUE)
            {
                return true;
            }
            
            if (jSONObject.get(api.getRequiredPermission()) != null && jSONObject.get(api.getRequiredPermission()) == Boolean.TRUE)
            {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        
        return false;
    }

}
