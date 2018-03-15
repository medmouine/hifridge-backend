package com.hifridge.HiFridge.controller;

import com.hifridge.HiFridge.Services.FridgeService;
import com.hifridge.HiFridge.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/fridge")
public class FridgeController {

    @Autowired
    private FridgeService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIngredient(@RequestBody Ingredient ingredient) {
        service.addIngredient(ingredient);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ingredient> getIngredients() {
        return service.getIngredient();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateIngredient(@RequestBody Ingredient ingredient) {
        service.updateIngredient(ingredient);
    }
}
