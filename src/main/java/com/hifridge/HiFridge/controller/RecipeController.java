package com.hifridge.HiFridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hifridge.HiFridge.Services.FridgeService;
import com.hifridge.HiFridge.model.Recipe;

/**
 * Created by jerome on 2017-11-18.
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @Autowired
  private FridgeService service;

  public RecipeController(FridgeService pService) {
    service = pService;
  }

  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  public Recipe getRecipe(@PathVariable("id") int id) {
    return service.getRecipe(id);
  }

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public List<Recipe> getRecipes() {
    return service.getRecipes();
  }

  @RequestMapping(value = "/get/byIngredients", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<Recipe> getRecipeWithIngredients(@RequestBody int... ids) {
    return service.getRecipesWithIngredients(ids);
  }

  @RequestMapping(value = "/cook/{id}", method = RequestMethod.POST)
  public boolean cookRecipe(@PathVariable("id") int id) {
    return service.cookRecipe(id);
  }
}
