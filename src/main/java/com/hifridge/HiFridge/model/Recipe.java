package com.hifridge.HiFridge.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

  public int recipeId;
  public List<Ingredient> ingredients;

  public Recipe() {
    ingredients = new ArrayList<>();
  }

  public Recipe(int recipeId, List<Ingredient> ingredients) {
    this.recipeId = recipeId;
    this.ingredients = ingredients;
  }
}
