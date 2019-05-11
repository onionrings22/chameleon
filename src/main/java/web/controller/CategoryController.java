package web.controller;

import db.model.Category;
import db.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.service.CategoryService;
import web.service.GameService;
import web.service.UserService;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
       this.categoryService = categoryService;
    }

    @GetMapping("/category/get")
    @ResponseBody
    public Category getCategory() {
        return categoryService.getCategory();
    }

    @GetMapping("/items/get")
    @ResponseBody
    public List<String> getItems() {
        return categoryService.getItemsByCategory(getCategory().getName());
    }
}
