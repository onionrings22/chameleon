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

    @GetMapping("/done/get")
    @ResponseBody
    public boolean getDone() {
        return gameService.getDone();
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

    @GetMapping("/reveal")
    public String revealPage() {
        if (gameService.getReveal()) {
            return "views/reveal.html";
        } else {
            return "redirect:/votes";
        }
    }

    @GetMapping("/admin/ready")
    @ResponseBody
    public String setReady() {
        userService.setChameleon();
        categoryService.randomCategoryItem();
        gameService.makeReady();
        return "done";
    }

    @GetMapping("/admin/endVoting")
    @ResponseBody
    public String endVoting() {
        gameService.endVoting();
        return "done";
    }

    @GetMapping("/admin/reveal")
    @ResponseBody
    public String setReveal() {
        gameService.setReveal();
        return "done";
    }

    @GetMapping("reveal/get")
    @ResponseBody
    public boolean getReveal() {
        return gameService.getReveal();
    }

    @GetMapping("/admin/resetGame")
    @ResponseBody
    public String resetGame() {
        gameService.resetGame();
        userService.resetChameleon();
        userService.resetVotes();
        categoryService.resetCategory();
        return "done";
    }

    @GetMapping("/admin/offline")
    @ResponseBody
    public void setAllOffline() {
        userService.setAllOffline();
    }
}
