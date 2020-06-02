package com.ezgroceries.shoppinglist.cocktail.model;

import java.util.List;
import java.util.UUID;

public class CocktailResource extends Cocktail {

    private UUID cocktailId;

    public CocktailResource(UUID cocktailId, String name, String glass, String recipe, String imageUri, List<String> ingredients) {
        super(name,glass, recipe, imageUri, ingredients);
        this.cocktailId = cocktailId;
    }

    public String getCocktailId() {
        return cocktailId.toString();
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }

}
