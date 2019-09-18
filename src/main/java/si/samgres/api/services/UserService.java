package si.samgres.api.services;

import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.SimpleUser;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

@Service
public class UserService {
    public String getData(String token) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //create simple user
        SimpleUser user = new SimpleUser(userWrap.getUser());
        if (user == null) {
            return "false";
        }

        //return
        return GsonHelper.toJson(user);
    }

    public String changeData(String token, String password, String newEmail, String newFullname, String newPhone_number, String newPassword) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //unwrap user
        User user = userWrap.getUser();
        if (!user.getPassword().equals(password)) {
            return "false";
        }

        //change data
        if (newEmail.length() > 0 && !newEmail.equals("") && !user.getEmail().equals(newEmail)) {
            user.setEmail(newEmail);
        }
        if (newFullname.length() > 0 && !newFullname.equals("") && !user.getFullname().equals(newFullname)) {
            user.setFullname(newFullname);
        }
        if (newPhone_number.length() > 0 && !newPhone_number.equals("") && !user.getPhone_number().equals(newPhone_number)) {
            user.setPhone_number(newPhone_number);
        }
        if (newPassword.length() > 0 && !newPassword.equals("") && !user.getPassword().equals(newPassword)) {
            user.setPassword(newPassword);
        }

        //wrap user again
        userWrap.setUser(user);

        try {
            //update in database
            DatabaseManager.update(user);

            //update in tokenmanager
            TokenManager.setUserWithExistingToken(token, userWrap);

            //success
            return "true";
        }catch (Exception e) {
            e.printStackTrace();

            //fail
            return "false";
        }
    }
}
