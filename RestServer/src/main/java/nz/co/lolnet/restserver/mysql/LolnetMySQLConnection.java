/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.restserver.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class LolnetMySQLConnection {

    private static HashMap<String,String> URL = new HashMap<String, String>();
    private static HashMap<String,String> USERNAME = new HashMap<String, String>();
    private static HashMap<String,String> PASSWORD = new HashMap<String, String>();
    public LolnetMySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LolnetMySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        setupLolConConnection();
    }

    
    private static void setupLolConConnection() {
        //TODO get the stuff from Config. Need to discuss with cptwin what he wants
        URL.put("lolcon","jdbc:mysql://IPGOESHERE:3306/lolcoins"); 
        USERNAME.put("lolcon","USERNAME"); 
        PASSWORD.put("lolcon","PASSWORD");
    }

    public static Connection getLolConConnection() throws SQLException {
        return DriverManager.getConnection(URL.get("lolcon"), USERNAME.get("lolcon"), PASSWORD.get("lolcon"));
    }

}
