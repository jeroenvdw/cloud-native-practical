package com.ezgroceries.shoppinglist.database.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "cocktail_shopping_list")
@IdClass(CocktailShoppingListEntity.class)
public class CocktailShoppingListEntity implements Serializable {

    @Id
    @Column(name = "cocktail_id")
    private UUID cocktailId;

    @Id
    @Column(name = "shopping_list_id")
    private UUID shoppingListId;

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

}
