package si.samgres.api.managers.authentication;

import si.samgres.api.helpers.TokenGenerator;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TokenManager {
    private static HashMap<String, AuthenticatedUser> authenticatedUsers;

    private static void initialize() {
        if (authenticatedUsers == null) { //ensure singleton
            authenticatedUsers = new HashMap<>();
        }
    }

    public static boolean refreshToken(String token) {
        //check if it even exists
        if (authenticatedUsers.containsKey(token)) {
            //get authenticated user
            AuthenticatedUser au = authenticatedUsers.get(token);
            au.setDate(getValidDate());

            //success
            return true;
        }

        //fail
        return false;
    }

    public static String signIn(User user) {
        initialize(); //ensure list

        //try signing in a user
        try {
            //create authenticated user
            AuthenticatedUser au = new AuthenticatedUser(user, getValidDate());

            //generate id
            String token = TokenGenerator.generate();

            //sign in
            authenticatedUsers.put(token, au);

            //success
            return token;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //return
        return "false";
    }

    public static boolean isTokenValid(String token) {
        initialize(); //ensure objects

        //boolean for validity
        boolean valid = false;
        if (authenticatedUsers.containsKey(token)) {
            //get authenticated user
            AuthenticatedUser au = authenticatedUsers.get(token);

            //get todays date
            Date today = new Date();

            //check if todays date is past the date of authenticated user
            if (today.compareTo(au.getDate()) <= 0) { //it hasn't expired yet
                valid = true;
            }
            else {
                authenticatedUsers.remove(token);
            }
        }

        return valid;
    }

    private static Date getValidDate() {
        //get todays date
        Date today = new Date();

        //add one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 1); //add one day

        return calendar.getTime();
    }

    public static AuthenticatedUser getUser(String token) {
        //check if user even exists with this token
        if (isTokenValid(token)) {
            //success
            return authenticatedUsers.get(token);
        }

        //fail
        return null;
    }

    public static boolean setUserWithExistingToken(String token, AuthenticatedUser authenticatedUser) {
        //check if user even exists with this token
        if (isTokenValid(token)) {
            //remove user
            authenticatedUsers.remove(token);

            //insert user again
            authenticatedUsers.put(token, authenticatedUser);

            //success
            return true;
        }

        //fail
        return false;
    }
}
