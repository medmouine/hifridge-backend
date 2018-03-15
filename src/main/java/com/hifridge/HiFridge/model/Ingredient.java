package com.hifridge.HiFridge.model;


import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private int ingredientId;
    private String name;
    private List<IngredientData> ingredientDatas;

    public Ingredient(int ingredientId, List<IngredientData> ingredientDatas, String name) {
        this.ingredientId = ingredientId;
        this.ingredientDatas = ingredientDatas;
        this.name = name;
    }

    public Ingredient() {
        ingredientDatas = new ArrayList<>();
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public List<IngredientData> getIngredientDatas() {
        return ingredientDatas;
    }

    public void setIngredientDatas(List<IngredientData> ingredientDatas) {
        this.ingredientDatas = ingredientDatas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
