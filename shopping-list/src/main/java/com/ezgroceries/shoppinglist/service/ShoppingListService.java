package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.database.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.database.ShoppingListRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingListResource create(ShoppingListResource shoppingListResource) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(UUID.fromString(shoppingListResource.getShoppingListId()));
        shoppingListEntity.setName(shoppingListResource.getName());
        shoppingListRepository.save(shoppingListEntity);
        return shoppingListResource;
    }
}
