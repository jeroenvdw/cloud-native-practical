package com.ezgroceries.shoppinglist.cocktail.model;

import com.ezgroceries.shoppinglist.cocktail.external.CocktailDBResponse;
import java.util.List;
import java.util.UUID;

public class CocktailResource extends Cocktail {

    private UUID cocktailId;

    public CocktailResource(UUID cocktailId, String name, String glass, String recipe, String imageUri, List<String> ingredients) {
        super(name,glass, recipe, imageUri, ingredients);
        this.cocktailId = cocktailId;
    }

    public CocktailResource(CocktailDBResponse.DrinkResource drinkResource) {
        super(drinkResource.getStrDrink(), drinkResource.getStrGlass(), drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), drinkResource.getAllIngredients());
        cocktailId = UUID.randomUUID();
    }

    public String getCocktailId() {
        return cocktailId.toString();
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }

}
