package lv.team3.botcovidlab.manager.controller;

import lv.team3.botcovidlab.manager.model.User;
import lv.team3.botcovidlab.manager.security.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class AuthenticationController {
    UserDetailsServiceImpl userDetailsServiceImpl ;

    public AuthenticationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsServiceImpl = userDetailsService;
    }

    @GetMapping("/login")
    public String getSignInPage(){
        return "login";
    }
    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }
    @PostMapping("/create-new-user")
    public String createUser(@ModelAttribute("user") User user){
        userDetailsServiceImpl.saveSimpleUser(user);
        return "redirect:/success";
    }

}
