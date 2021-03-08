package learn.spring.springsecure.controller;




/*
 * @author
 * @version
 * @return
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthUsers() {
        return "secured part of web service";
    }

}
