package web.controller;

import db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
       this.userService = userService;
    }

    @GetMapping("/online/get")
    @ResponseBody
    public List<String> getOnlineUsernames() {
        List<User> users = userService.getAllOnline();
        List<String> usernames = new ArrayList<>();
        for(User user: users) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @GetMapping("/chameleon/get")
    @ResponseBody
    public String getChameleon() {
        User user = userService.getChameleon();
        return user.getUsername();
    }

    @GetMapping("/vote")
    @ResponseBody
    public void vote(HttpSession session, @RequestParam String suspect) {
        SecurityContextImpl security = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserDetails userDetails = (UserDetails) security.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        userService.vote(username, suspect);
    }

    @GetMapping("/votes/get")
    @ResponseBody
    public List<String> getVotes() {
        return userService.getVotes();
    }
}
