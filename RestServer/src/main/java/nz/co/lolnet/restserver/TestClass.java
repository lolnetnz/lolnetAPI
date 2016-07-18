/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.restserver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.simple.JSONObject;

/**
 *
 * @author James
 */
public class TestClass {

    private static final String TARGET_URI = "http://localhost:8080/lolcoins";
    private static String NAME = "world";
    private static WebTarget target;

    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        Client client = ClientBuilder.newClient(clientConfig);
        JSONObject serverInfo = new JSONObject();
        serverInfo.put("toket", "abc123randompassword");
        NAME = serverInfo.toJSONString();
        System.out.println(serverInfo.toJSONString());

        target = client.target(TARGET_URI);
        String result = (String) target.path(NAME).request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        System.out.println(result);
    }
}
