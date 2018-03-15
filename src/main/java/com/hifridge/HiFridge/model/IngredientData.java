package com.hifridge.HiFridge.model;

/**
 * Created by jerome on 2017-11-18.
 */

public class IngredientData {


  public int quantity;
  public String expirationDate;

  public IngredientData(int quantity, String expirationDate) {
    this.quantity = quantity;
    this.expirationDate = expirationDate;
  }

  public IngredientData() {}

  public int getQuantity() {
    return quantity;
  }

  public void ReduceQuantity(int quantityToRemove) {
    quantity -= quantityToRemove;
  }

  public String getExpirationDate() {
    return expirationDate;
  }
}
