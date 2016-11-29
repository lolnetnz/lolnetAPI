/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.lolnet.lolnetapi.sponge;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 *
 * @author James
 */
@ConfigSerializable
class MyConfig {

    private static final String API_KEY = "APIKey";
    private static final String API_KEY_COMMENT = "API Key to access Lolnet's API.";
    @Setting(value = API_KEY, comment = API_KEY_COMMENT)
    private String apiKey = "";

    public String getApiKey() {
        return apiKey;
    }
}
