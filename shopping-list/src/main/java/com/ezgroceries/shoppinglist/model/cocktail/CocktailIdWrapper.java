package com.ezgroceries.shoppinglist.model.cocktail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class CocktailIdWrapper {

    private UUID cocktailId;

    public CocktailIdWrapper(@JsonProperty("cocktailId") String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);;
    }

    public String getCocktailId() {
        return cocktailId.toString();
    }

    private void setCocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }

}
