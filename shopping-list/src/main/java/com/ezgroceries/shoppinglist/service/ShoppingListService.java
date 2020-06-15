package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.database.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.database.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.database.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.database.ShoppingListRepository;
import com.ezgroceries.shoppinglist.model.cocktail.CocktailResource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailShoppingListRepository cocktailShoppingListRepository;
    private final CocktailService cocktailService;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailShoppingListRepository cocktailShoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
        this.cocktailService = cocktailService;
    }

    public ShoppingListResource create(ShoppingListResource shoppingListResource) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(UUID.fromString(shoppingListResource.getShoppingListId()));
        shoppingListEntity.setName(shoppingListResource.getName());
        shoppingListRepository.save(shoppingListEntity);
        return shoppingListResource;
    }

    public ShoppingListResource getShoppingList(String uuid) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.getOne(UUID.fromString(uuid));
        return new ShoppingListResource(shoppingListEntity.getId(), shoppingListEntity.getName());
    }

    public List<CocktailResource> addCocktailsToShoppingList(List<String> cocktailIds, ShoppingListResource shoppingListResource) {
        List<CocktailResource> cocktailResources = new ArrayList<>();
        for(String cocktailId : cocktailIds) {
            cocktailResources.add(addCocktailToShoppingList(cocktailId, shoppingListResource));
        }
        return cocktailResources;
    }

    public CocktailResource addCocktailToShoppingList(String cocktailId, ShoppingListResource shoppingListResource) {
        CocktailResource cocktailResource = cocktailService.getCocktailByUuid(cocktailId);
        CocktailShoppingListEntity cocktailShoppingListEntity = new CocktailShoppingListEntity();
        cocktailShoppingListEntity.setCocktailId(UUID.fromString(cocktailResource.getCocktailId()));
        cocktailShoppingListEntity.setShoppingListId(UUID.fromString(shoppingListResource.getShoppingListId()));
        cocktailShoppingListRepository.save(cocktailShoppingListEntity);
        return cocktailResource;
    }
}
