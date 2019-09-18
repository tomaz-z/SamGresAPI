package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.samgres.api.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public String authenticateUser(@RequestHeader String email,@RequestHeader String password) {
        return loginService.authenticateUser(email, password);
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public String refreshToken(@RequestHeader String token) {
        return loginService.refreshToken(token);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@RequestHeader String phone, @RequestHeader String password, @RequestHeader String fullname, @RequestHeader String email) {
        return loginService.registerNewUser(phone, password, fullname, email);
    }
}
