package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.service.CategoryService;
import web.service.GameService;
import web.service.UserService;

@Controller
public class GameController {

    private GameService gameService;
    private UserService userService;
    private CategoryService categoryService;

    @Autowired
    public GameController(GameService gameService, UserService userService, CategoryService categoryService) {
       this.gameService = gameService;
       this.userService = userService;
       this.categoryService = categoryService;
    }

    @GetMapping("/ready/get")
    @ResponseBody
    public boolean getReady() {
        return gameService.getReady();
    }

    @GetMapping("/votingDone/get")
    @ResponseBody
    public boolean getVotingDone() {
        return gameService.getVotingDone();
    }

    @GetMapping("/items")
    public String itemsPage() {
        if (gameService.getReady()) {
            return "views/items.html";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/votes")
    public String votesPage() {
        if (gameService.getVotingDone()) {
            return "views/votes.html";
        } else {
            return "redirect:/items";
        }
    }

    @RequestMapping("/admin/ready")
    public String setReady() {
        userService.setChameleon();
        categoryService.randomCategoryItem();
        gameService.makeReady();
        return "views/items.html";
    }

    @RequestMapping("/admin/endVoting")
    public String endVoting() {
        gameService.endVoting();
        return "views/votes.html";
    }

    @RequestMapping("/admin/resetGame")
    @ResponseBody
    public void resetGame() {
        gameService.resetGame();
        userService.resetChameleon();
        userService.resetVotes();
        categoryService.resetCategory();
    }

    @RequestMapping("/admin/offline")
    @ResponseBody
    public void setAllOffline() {
        userService.setAllOffline();
    }
}
