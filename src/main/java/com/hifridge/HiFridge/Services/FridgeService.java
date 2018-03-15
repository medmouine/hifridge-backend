package com.hifridge.HiFridge.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hifridge.HiFridge.model.Ingredient;
import com.hifridge.HiFridge.model.IngredientData;
import com.hifridge.HiFridge.model.Recipe;

@Service
public class FridgeService {

  private HashMap<Integer, Recipe> recipes;
  private HashMap<Integer, Ingredient> ingredients;

  public FridgeService() {
    recipes = new HashMap<>();
    ingredients = new HashMap<>();
  }

  public Recipe getRecipe(int id) {
    if (recipes.isEmpty()) {
      this.createRecipes();
    }

    return recipes.get(id);
  }

  public List<Recipe> getRecipes() {
    if (recipes.isEmpty()) {
      this.createRecipes();
    }
    return recipes.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
  }

  public boolean cookRecipe(int recipeId) {
    if (canCook(recipeId)) {
      Recipe recipe = recipes.get(recipeId);

      for (Ingredient ingredient : recipe.ingredients) {
        List<IngredientData> datas = ingredients.get(ingredient.getIngredientId()).getIngredientDatas();

        datas.sort(new Comparator<IngredientData>() {
          @Override
          public int compare(IngredientData o1, IngredientData o2) {
            return Date.valueOf(o1.getExpirationDate()).compareTo(Date.valueOf(o2.getExpirationDate()));
          }
        });

        int quantityToRemove = ingredient.getIngredientDatas().stream().mapToInt(x -> x.getQuantity()).sum();

        for (int i = datas.size(); i > 0 && quantityToRemove > 0; i--) {
          if (datas.get(i).getQuantity() <= quantityToRemove) {
            quantityToRemove -= datas.get(i).getQuantity();
            datas.remove(i);
          } else if (datas.get(i).getQuantity() > quantityToRemove) {
            datas.get(i).ReduceQuantity(quantityToRemove);
          }
        }

        ingredient.setIngredientDatas(datas);
        ingredients.put(ingredient.getIngredientId(), ingredient);
      }

      return true;
    }

    return false;
  }

  public List<Recipe> getRecipesWithIngredients(int... recipeIngredients) {
    return recipes.entrySet().stream().map(x -> x.getValue())
        .filter(x -> x.ingredients.stream().anyMatch(y -> Arrays.stream(recipeIngredients).anyMatch(z -> 0 == Integer.compare(y.getIngredientId(), z)))).collect(Collectors.toList());
  }

  private void createRecipes() {
    List<Ingredient> ingredientsPossible = getIngredient();
    ingredientsPossible = ingredientsPossible.subList(0, ingredientsPossible.size() - 2);
    Recipe recipe = new Recipe(1, ingredientsPossible);
    recipes.put(recipe.recipeId, recipe);

    List<Ingredient> ingredientsPossible2 = getIngredient();
    ingredientsPossible2 = ingredientsPossible.subList(ingredientsPossible2.size() - 3, ingredientsPossible2.size() - 2);
    Recipe recipe2 = new Recipe(2, ingredientsPossible2);
    recipes.put(recipe2.recipeId, recipe2);
  }

  private boolean canCook(int recipeId) {
    Recipe recipe = recipes.get(recipeId);

    for (Ingredient ingredient : recipe.ingredients) {
      if (ingredient.getIngredientDatas().stream().mapToInt(x -> x.quantity).sum() > ingredients.get(ingredient.getIngredientId()).getIngredientDatas().stream()
          .filter(x -> 0 >= Date.valueOf("2017/11/18").compareTo(Date.valueOf(x.expirationDate))).mapToInt(x -> x.quantity).sum()) {
        return false;
      }
    }

    return true;
  }

  private void createIngredients() {
    String[] expirationDates = new String[] {"2018/01/01", "2018/02/01", "2018/05/08", "2018/12/29", "2018/12/03", "2017/12/12"};
    String[] productNames = new String[] {"Céleri", "Poulet", "Tomate", "Pepperoni", "Salami", "Piment fort"};

    for (int i = 0; i < 6; i++) {
      IngredientData ingredientData1 = new IngredientData(ThreadLocalRandom.current().nextInt(2, 8), expirationDates[i]);
      List<IngredientData> ingredientDatas1 = new ArrayList<>();
      ingredientDatas1.add(ingredientData1);
      Ingredient ingredient1 = new Ingredient(i, ingredientDatas1, productNames[i]);
      ingredients.put(ingredient1.getIngredientId(), ingredient1);
    }
  }

  public void updateIngredient(Ingredient ingredient) {
    ingredients.put(ingredient.getIngredientId(), ingredient);
  }

  public List<Ingredient> getIngredient() {
    if (this.ingredients.isEmpty()) {
      this.createIngredients();
    }
    return ingredients.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
  }

  public void addIngredient(Ingredient ingredient) {
    ingredients.put(ingredient.getIngredientId(), ingredient);
  }
}
