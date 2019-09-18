package si.samgres.api.managers.authentication;

import si.samgres.api.models.DARS.authentication.Token;

import java.util.Calendar;
import java.util.Date;

public class DARSTokenManager {
    private static Token currentDARSToken;
    private static Date tokenValidUntil;

    public static Token getCurrentDARSToken() {
        return currentDARSToken;
    }

    public static void setCurrentDARSToken(Token currentDARSToken) {
        //set new token
        DARSTokenManager.currentDARSToken = currentDARSToken;

        //set token validity object
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(DARSTokenManager.getCurrentDARSToken().getExpires_in()));
        setTokenValidUntil(calendar.getTime());
    }

    public static Date getTokenValidUntil() {
        return tokenValidUntil;
    }

    public static void setTokenValidUntil(Date tokenValidUntil) {
        DARSTokenManager.tokenValidUntil = tokenValidUntil;
    }

    public static boolean isTokenStillValid() {
        if (getCurrentDARSToken() == null || getTokenValidUntil() == null) { //flag
            return false;
        }

        //get current time
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        //check for expiration validity
        if (currentTime.after(getTokenValidUntil())) {
            return false;
        }

        //its still valid
        return true;
    }
}
