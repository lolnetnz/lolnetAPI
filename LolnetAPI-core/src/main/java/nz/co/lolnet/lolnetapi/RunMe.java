/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi;

import java.util.HashMap;
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
            int forumID = 0;
            String playerName = "James137137";
            forumID = lolCon.getForumUserID(null, playerName);
            System.out.println(forumID);
            System.out.println(lolCon.setForumMinecraftLinkGroup(forumID, null));
            /*System.out.println("removing all");
            for (int id : upgrades.keySet()) {
            System.out.println(lolCon.removeUserUpgrade(id, null));
            }
            System.out.println(lolCon.getUpgrades(214, null));
            System.out.println(lolCon.getUserUpgradeInfo(18, null));*/
        } catch (Exception ex) {
            Logger.getLogger(RunMe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
