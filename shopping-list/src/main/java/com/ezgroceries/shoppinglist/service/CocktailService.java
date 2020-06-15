package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.database.CocktailRepository;
import com.ezgroceries.shoppinglist.database.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.service.external.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.service.external.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.model.CocktailResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository, CocktailDBClient cocktailDBClient) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDBClient = cocktailDBClient;
    }

    public List<CocktailResource> getCocktails(String search) {
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        if(cocktailDBResponse.getDrinks() == null) {
            //return empty list when a cocktail is not found
            return new ArrayList<>();
        }
        return mergeCocktails(cocktailDBResponse.getDrinks());
    }

    public CocktailResource getCocktailByUuid(String cocktailUuid) {
        //search on local database (as we search on our own uuid, it must be in the db already)
        CocktailEntity cocktailEntity = cocktailRepository.findById(UUID.fromString(cocktailUuid)).get();
        //get details out of external api
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktailById(cocktailEntity.getIdDrink());
        DrinkResource drinkResource = cocktailDBResponse.getDrinks().get(0);
        return new CocktailResource(cocktailEntity.getId(),
                cocktailEntity.getName(),
                drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(),
                drinkResource.getStrDrinkThumb(),
                drinkResource.getAllIngredients());
    }

    private List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setId(UUID.randomUUID());
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), drinkResource.getAllIngredients())).collect(Collectors.toList());
    }
}
