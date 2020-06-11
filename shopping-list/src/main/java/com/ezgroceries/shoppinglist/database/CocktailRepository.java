package com.ezgroceries.shoppinglist.database;

import com.ezgroceries.shoppinglist.database.entities.CocktailEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {
}
