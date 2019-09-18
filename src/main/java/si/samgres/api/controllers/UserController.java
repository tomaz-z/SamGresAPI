package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public String getData(@RequestHeader String token) {
        return userService.getData(token);
    }

    @RequestMapping(value = "/changeData", method = RequestMethod.POST)
    public String changeData(@RequestHeader String token, @RequestHeader String password, @RequestHeader String newEmail, @RequestHeader String newFullname, @RequestHeader String newPhone_number, @RequestHeader String newPassword) {
        return userService.changeData(token, password, newEmail, newFullname, newPhone_number, newPassword);
    }
}
