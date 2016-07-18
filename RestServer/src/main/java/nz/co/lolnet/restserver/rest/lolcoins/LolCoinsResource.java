package nz.co.lolnet.restserver.rest.lolcoins;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.glassfish.jersey.server.ResourceConfig;

@Path("/lolcoins")
public class LolCoinsResource {

    public LolCoinsResource(ResourceConfig resourceConfig) {
        resourceConfig.packages(LolCoinsResource.class.getPackage().getName());
        resourceConfig.packages(GetPlayerBalance.class.getPackage().getName());
    }

    @GET
    public String noInputHere() {
        return "Help info should go here";
    }
}
