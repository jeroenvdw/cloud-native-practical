package com.ezgroceries.shoppinglist.service.external;

import com.ezgroceries.shoppinglist.database.CocktailRepository;
import com.ezgroceries.shoppinglist.database.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.service.external.CocktailDBClient.CocktailDBClientFallback;
import com.ezgroceries.shoppinglist.service.external.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.service.external.CocktailDBResponse.DrinkResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @GetMapping(value = "lookup.php")
    CocktailDBResponse searchCocktailById(@RequestParam("i") String id);

    @Component
    class CocktailDBClientFallback implements CocktailDBClient {

        private final CocktailRepository cocktailRepository;

        public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
            this.cocktailRepository = cocktailRepository;
        }

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                drinkResource.setStrGlass(cocktailEntity.getGlass());
                drinkResource.setStrInstructions(cocktailEntity.getInstructions());
                drinkResource.setStrDrinkThumb(cocktailEntity.getImageLink());
                drinkResource.setAllIngredients(new ArrayList<>(cocktailEntity.getIngredients()));
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }

        @Override
        public CocktailDBResponse searchCocktailById(String id) {
            CocktailEntity cocktailEntity = cocktailRepository.findById(UUID.fromString(id)).get();
            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            DrinkResource drinkResource = new DrinkResource();
            drinkResource.setIdDrink(cocktailEntity.getIdDrink());
            drinkResource.setStrDrink(cocktailEntity.getName());
            drinkResource.setStrGlass(cocktailEntity.getGlass());
            drinkResource.setStrInstructions(cocktailEntity.getInstructions());
            drinkResource.setStrDrinkThumb(cocktailEntity.getImageLink());
            drinkResource.setAllIngredients(new ArrayList<>(cocktailEntity.getIngredients()));
            cocktailDBResponse.setDrinks(Arrays.asList(drinkResource));
            return cocktailDBResponse;
        }
    }
}
