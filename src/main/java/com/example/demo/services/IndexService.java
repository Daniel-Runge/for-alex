package com.example.demo.services;

import com.example.demo.domain.Recipe;
import com.example.demo.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndexService {

    RecipeRepository recipeRepository;

    public IndexService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipesBySearchString(String searchString) {

        return recipeRepository.findByNameContains(searchString);
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Optional<Recipe> findById(long id) {
        return recipeRepository.findById(id);
    }

    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }
}
