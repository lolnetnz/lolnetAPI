package nz.co.lolnet.lolnetapi.sponge;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import nz.co.lolnet.lolnetapi.settings.Settings;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 *
 * @author cptwin and james
 */

@Plugin(id = "lolnetapi", name = "LolnetAPI", version = "0.0.1", 
        description = "LolnetAPI Plugin", authors = {"james137137", "lx_gaming", "cptwin"}, url = "https://www.lolnet.co.nz/")
public class lolnetAPI {

    @Inject
    private Game game;

    @Inject
    public Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    private GuiceObjectMapperFactory factory;

    private MyConfig config;

    @Listener
    public void onStart(GameLoadCompleteEvent event) {
        loadConfig();
        setupAPIKey();
    }

    private void loadConfig() {
        try {
            CommentedConfigurationNode node = loader.load(ConfigurationOptions.defaults().setObjectMapperFactory(factory).setShouldCopyDefaults(true));
            config = node.getValue(TypeToken.of(MyConfig.class), new MyConfig());
            loader.save(node);
            logger.info("Successfully loaded configuration file.");
        } catch (IOException | ObjectMappingException ex) {
            logger.error("Exception loading configuration file.");
            ex.printStackTrace();
        }
    }

    private void setupAPIKey() {
        String api = config.getApiKey().substring(0, 10);
        logger.info("LolnetAPI API: " + api + "...");
        Settings.setAPIKey(config.getApiKey());
        logger.info("Quick test for the API");
        logger.info("James137137 balance is:");
        try {
            logger.info(Long.toString(nz.co.lolnet.lolnetapi.lolcon.lolCon.getPlayerBalance("james137137")));
        } catch (IOException | ParseException ex) {
            java.util.logging.Logger.getLogger(lolnetAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
