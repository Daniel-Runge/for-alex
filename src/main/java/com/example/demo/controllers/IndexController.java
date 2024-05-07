package com.example.demo.controllers;

import com.example.demo.domain.Recipe;
import com.example.demo.services.IndexService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/")
public class IndexController {

    IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("")
    public String getAllRecipes(Model model) {
        List<Recipe> recipes = indexService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }

    @GetMapping("/create")
    public String showCreate(@ModelAttribute Recipe recipe, Model model) {
        return "add-recipe";
    }

    @PostMapping("/addrecipe")
    public String addUser(@ModelAttribute @Valid Recipe recipe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-recipe";
        }

        indexService.save(recipe);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Recipe recipe = indexService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));

        model.addAttribute("recipe", recipe);
        return "update-recipe";
    }

    @PostMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") long id, @Valid Recipe recipe,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            recipe.setId(id);
            return "update-recipe";
        }

        indexService.save(recipe);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") long id, Model model) {
        Recipe recipe = indexService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        indexService.delete(recipe);
        return "redirect:/";
    }

}
