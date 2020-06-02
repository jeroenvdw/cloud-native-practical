package com.ezgroceries.shoppinglist.cocktail.model;

import java.util.List;

public class Cocktail {

    private String name;
    private String glass;
    private String recipe;
    private String imageUri;
    private List<String> ingredients;

    public Cocktail(String name, String glass, String recipe, String imageUri, List<String> ingredients) {
        this.name = name;
        this.glass = glass;
        this.recipe = recipe;
        this.imageUri = imageUri;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public List<String>  getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String>  ingredients) {
        this.ingredients = ingredients;
    }

}
