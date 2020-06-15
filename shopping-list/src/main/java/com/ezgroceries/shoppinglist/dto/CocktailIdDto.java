package com.ezgroceries.shoppinglist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class CocktailIdDto {

    private UUID cocktailId;

    public CocktailIdDto(@JsonProperty("cocktailId") String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);;
    }

    public String getCocktailId() {
        return cocktailId.toString();
    }

    private void setCocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }

}
