package web.controller;

import db.dao.UserRepository;
import db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    @Transactional
    public String root(HttpSession session) {
        SecurityContextImpl security = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserDetails userDetails = (UserDetails) security.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        if (!user.isOnline()) {
            user.setOnline(true);
            userRepository.save(user);
        }
        return "views/index.html";
    }

    @RequestMapping("/user/offline")
    @Transactional
    public String logUserOut(HttpSession session) {
        SecurityContextImpl security = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserDetails userDetails = (UserDetails) security.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        user.setOnline(false);
        userRepository.save(user);
        return "redirect:/logout";
    }

    @GetMapping("/login")
    public String login_container() {
        return "views/login_container.html";
    }

    @GetMapping("/register")
    public String register() {
        return "views/register.html";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUser(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setActive(true);
            user.setRole("user");
            userRepository.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/username/get")
    @ResponseBody
    public String getUsername(HttpSession session) {
        SecurityContextImpl security = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserDetails userDetails = (UserDetails) security.getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
