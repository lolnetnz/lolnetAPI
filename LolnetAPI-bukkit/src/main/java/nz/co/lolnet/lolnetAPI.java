package nz.co.lolnet;

import nz.co.lolnet.lolnetapi.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author cptwin and james
 */
public class lolnetAPI extends JavaPlugin {

    @Override
    public void onLoad() {
        setupAPIKey();
    }

    @Override
    public void onEnable() {
        setupAPIKey();
    }
    
    

    private void setupAPIKey() {
        this.saveDefaultConfig();
        String api = this.getConfig().getString("LolnetAPISecretKey").substring(0, 10);
        Bukkit.getLogger().info("LolnetAPI API: " + api + "...");
        Settings.setAPIKey(this.getConfig().getString("LolnetAPISecretKey"));
    }
    
    
}
