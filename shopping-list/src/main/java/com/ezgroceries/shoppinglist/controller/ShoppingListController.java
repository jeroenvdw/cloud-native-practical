package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.external.CocktailDBClient;
import com.ezgroceries.shoppinglist.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.cocktail.CocktailIdWrapper;
import com.ezgroceries.shoppinglist.model.cocktail.CocktailResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {

    @PostMapping("/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createNewShoppingList(@RequestBody ShoppingList shoppingList) {
        return new ShoppingListResource(shoppingList.getName());
    }

    @PostMapping("/shopping-lists/{uuid}/cocktails")
    public List<CocktailIdWrapper> addCocktailToShoppingList(@RequestBody List<CocktailIdWrapper> cocktails, @PathVariable("uuid") String uuid) {
        return cocktails;
    }

    @GetMapping("/shopping-lists/{uuid}")
    public ShoppingListResource getShoppingList(@PathVariable("uuid") String uuid) {
        //creating dummy list
        ShoppingListResource shoppingListResource = new ShoppingListResource("Dummy List");
        shoppingListResource.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Salt"));
        return shoppingListResource;
    }

    @GetMapping("/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {
        //creating dummy lists
        ShoppingListResource shoppingListResource1 = new ShoppingListResource("Dummy List");
        shoppingListResource1.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Salt"));
        ShoppingListResource shoppingListResource2 = new ShoppingListResource("Dummy List 2");
        shoppingListResource2.setIngredients(Arrays.asList("Tequilq", "Vodka", "Beer"));
        return Arrays.asList(shoppingListResource1,shoppingListResource2);
    }
}
