package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.CocktailIdDto;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping("/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createNewShoppingList(@RequestBody ShoppingList shoppingList) {
        ShoppingListResource shoppingListResource = new ShoppingListResource(shoppingList.getName());
        shoppingListService.create(shoppingListResource);
        return shoppingListResource;
    }

    @PostMapping("/shopping-lists/{uuid}/cocktails")
    public List<CocktailIdDto> addCocktailToShoppingList(@RequestBody List<CocktailIdDto> cocktails, @PathVariable("uuid") String uuid) {
        ShoppingListResource shoppingListResource = getShoppingList(uuid);
        List<CocktailIdDto> cocktailIds = new ArrayList<>();
        for(CocktailIdDto cocktailId : cocktails) {
            CocktailResource cocktailResource = shoppingListService.addCocktailToShoppingList(cocktailId.getCocktailId(), shoppingListResource);
            cocktailIds.add(new CocktailIdDto(cocktailResource.getCocktailId()));
        }
        return cocktailIds;
    }

    @GetMapping("/shopping-lists/{uuid}")
    public ShoppingListResource getShoppingList(@PathVariable("uuid") String uuid) {
        ShoppingListResource shoppingListResource = shoppingListService.getShoppingList(uuid);
        return shoppingListResource;
    }

    @GetMapping("/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }
}
