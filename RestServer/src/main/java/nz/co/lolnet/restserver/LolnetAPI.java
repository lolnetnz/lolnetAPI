/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.restserver;

import javax.ws.rs.GET;

/**
 *
 * @author James
 */
public class LolnetAPI {
    private String requiredPermission;

    public LolnetAPI(String requiredPermission) {
        this.requiredPermission = requiredPermission;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }
    
    
    
}
