package nz.co.lolnet.lolnetapi.settings;

public class Settings {
    public static final int httpTimeOut = 10000;
    private static String APIKey;

    public static void setAPIKey(String APIKey) {
        Settings.APIKey = APIKey;
    }

    public static String checkAPIKey(String authHash) {
        if (authHash == null) {
            authHash = APIKey;
        }
        return authHash;
    }

}
