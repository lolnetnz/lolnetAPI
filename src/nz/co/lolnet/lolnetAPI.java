package nz.co.lolnet;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author cptwin
 */
public class lolnetAPI extends JavaPlugin {
    
    public static final Logger logger = Logger.getLogger("Minecraft"); //creates the logger for outputting errors or information to the server console
    public static lolnetAPI plugin; //a static variable for the plugin so that we can do things like register listeners or get players from another class
    
    /**
     * This method is always called when the plugin is first loaded, it is usually where you put everything that needs to be setup.
     */
    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        lolnetAPI.logger.log(Level.INFO, "{0} Version {1} Has Been Enabled!", new Object[]{pdfFile.getName(), pdfFile.getVersion()}); //outputs to console that we have enabled our plugin
    }
     
    /**
     * This method is always called when the plugin is disabled, it can be disabled multiple ways from the server shutting down to another plugin disabling it.
     */   
    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        lolnetAPI.logger.log(Level.INFO, "{0} Version {1} Has Been Disabled!", new Object[]{pdfFile.getName(), pdfFile.getVersion()}); //outputs to console that we have disabled our plugin
    }
    
}
