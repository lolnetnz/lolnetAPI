/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi;

import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.lolnet.lolnetapi.lolcon.lolCon;
import nz.co.lolnet.lolnetapi.settings.Settings;

/**
 *
 * @author James
 */
public class RunMe {
    public static void main(String[] args) {
        Settings.setAPIKey("");
        try {
            boolean playerExist = lolCon.playerExists(null, "James1337");
            boolean result = lolCon.registerNewPlayer(null, "James1337");
            System.out.println(result);
            System.out.println(playerExist);
            System.out.println(lolCon.playerExists(null, "James1337"));
            System.out.println("");
        } catch (Exception ex) {
            Logger.getLogger(RunMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
