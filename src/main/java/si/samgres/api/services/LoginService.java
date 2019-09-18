package si.samgres.api.services;


import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LoginService {
    public boolean checkUserTokenValidity(String token) {
        //check validity
        return TokenManager.isTokenValid(token);
    }

    public User getUser(String token) {
        if (!checkUserTokenValidity(token)) { //flag
            return null;
        }

        //get user
        AuthenticatedUser authenticatedUser = TokenManager.getUser(token);
        if (authenticatedUser == null) { //flag
            return null;
        }

        //success
        return authenticatedUser.getUser();
    }

    public String refreshToken(String token) {
        //check validity
        boolean done = false;
        if (TokenManager.isTokenValid(token)) {
            //skip refreshing token
            done = true;
        }
        else {
            //check with system and update valid date
            done = TokenManager.refreshToken(token);
        }

        //return
        return GsonHelper.toJson(done);
    }

    public String authenticateUser(String email, String password){
        //get users
        List<User> users = DatabaseManager.getAll(User.class);
        if (users == null) {
            return "false";
        }

        //try finding user
        for (int i = 0; i < users.size(); i++) {
            User current = users.get(i);

            //check credentials
            if (current.getEmail().equals(email) && current.getPassword().equals(password)) {
                return TokenManager.signIn(current);
            }
        }

        //fail
        return "false";
    }

    public String registerNewUser(String phone, String password, String fullname, String email)
    {
        //get current id
        ArrayList<User> users = (ArrayList<User>) DatabaseManager.getAll(User.class);
        int id = 0;
        if (users.size() > 0) {
            id = users.stream().max(Comparator.comparing(z -> z.getId())).get().getId();
            id++; //increase for new user
        }

        //create new user
        User user = new User(id, phone, password, fullname, email);

        //try adding user
        if (DatabaseManager.add(user)) {
            return "true";
        }
        else {
            return "false";
        }
    }
}
