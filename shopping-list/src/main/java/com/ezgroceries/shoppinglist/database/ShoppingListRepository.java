package com.ezgroceries.shoppinglist.database;

import com.ezgroceries.shoppinglist.database.entities.ShoppingListEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {
}
