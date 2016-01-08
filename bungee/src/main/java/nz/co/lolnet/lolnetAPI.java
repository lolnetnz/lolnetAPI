package nz.co.lolnet;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import nz.co.lolnet.lolnetapi.settings.Settings;

/**
 *
 * @author cptwin and james
 */
public class lolnetAPI extends Plugin {

    private Configuration config;
    @Override
    public void onLoad() {
        setupAPIKey();
    }
    
    
    private void setupAPIKey() {
        setupConfigFile();
        Settings.setAPIKey(config.getString("LolnetAPISecretKey"));
    }
    
    private  void setupConfigFile() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                if (getResourceAsStream("config.yml") == null) {
                    System.out.println("Failed to obtain config.yml from getResourceAsStream(\"config.yml\")");
                }
                try (InputStream is = getResourceAsStream("config.yml");
                        OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }
        try {
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException ex) {
            Logger.getLogger(lolnetAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            Logger.getLogger(lolnetAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
