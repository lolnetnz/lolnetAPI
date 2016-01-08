package nz.co.lolnet;

import nz.co.lolnet.lolnetapi.settings.Settings;
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

    private void setupAPIKey() {
        this.saveDefaultConfig();
        Settings.setAPIKey(this.getConfig().getString("LolnetAPISecretKey"));
    }
    
    
}
