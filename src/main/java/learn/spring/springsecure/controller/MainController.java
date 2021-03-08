package learn.spring.springsecure.controller;




/*
 * @author
 * @version
 * @return
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthUsers(Principal principal) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "secured part of web service  - " + principal.getName();
    }

}
