/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi;

import java.util.UUID;
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
            UUID uuid = UUID.fromString("96513543-3da9-4ec4-8b29-31b542921da1");
            System.out.println(lolCon.playerExists(null, uuid));
            lolCon.ChangePlayerName("James137137", uuid, null);
        } catch (Exception ex) {
            Logger.getLogger(RunMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
