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
            System.out.println(lolCon.playerExists(null, "james137137"));
            boolean updatetPlayerTitle = lolCon.ChangePlayerUUID("james137137", "NOTSET",null);
            System.out.println(lolCon.getPlayerName("NOTSET"));
            System.err.println(updatetPlayerTitle);
        } catch (Exception ex) {
            Logger.getLogger(RunMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
