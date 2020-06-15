package com.ezgroceries.shoppinglist.model;

import java.util.UUID;

public class ShoppingListResource extends ShoppingList {

    private UUID shoppingListId;

    public ShoppingListResource(String name) {
        super(name);
        this.shoppingListId = UUID.randomUUID();
    }

    public ShoppingListResource(UUID uuid, String name) {
        super(name);
        this.shoppingListId = uuid;
    }

    public String getShoppingListId() {
        return shoppingListId.toString();
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = UUID.fromString(shoppingListId);
    }

}
