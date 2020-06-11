package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.external.CocktailDBClient;
import com.ezgroceries.shoppinglist.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.cocktail.CocktailResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    @Autowired
    private CocktailDBClient cocktailDBClient;

    @GetMapping
    public List<CocktailResource> get(@RequestParam String search) {
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        List<CocktailResource> cocktails = new ArrayList<>();
        for(CocktailDBResponse.DrinkResource drinkResource : cocktailDBResponse.getDrinks()) {
            cocktails.add(new CocktailResource(drinkResource));
        }
        return cocktails;
    }

}
