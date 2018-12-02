package nz.co.lolnet.lolnetapi.settings;

import nz.co.lolnet.lolnetapi.APIKeyNotSetException;

public class Settings {
    @Deprecated
    public static final int httpTimeOut = 10000;
    public static final int connectTimeout = 10000;
    public static final int readTimeout = 30000;
    private static String APIKey = null;

    public static void setAPIKey(String APIKey) {
        Settings.APIKey = APIKey;
    }

    public static String checkAPIKey(String authHash) throws APIKeyNotSetException {
        if (authHash == null && APIKey == null)
        {
            throw new APIKeyNotSetException("API key has not been set. Check config.yml");
        }
        if (authHash == null) {
            authHash = APIKey;
        }
        return authHash;
    }

}
