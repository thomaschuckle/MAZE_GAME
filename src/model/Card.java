package model;

import java.util.Arrays;
import java.util.Random;

public class Card {

    // Ingredients for the card recipe
    private int ingredient1;     // First ingredient in the recipe
    private int ingredient2;     // Second ingredient in the recipe
    private int ingredient3;     // Third ingredient in the recipe
    
    private int[] recipe = new int[3];  // Array to store the recipe ingredients

    // Constructors ---------------------------------------------------------------------------------

    public Card(int ingredient1, int ingredient2, int ingredient3) {
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
    }

    // Getters and Setters --------------------------------------------------------------------------

    // Get and set first ingredient
    public int getIngredient1() {
        return ingredient1;
    }
    public void setIngredient1(int ingredient1) {
        this.ingredient1 = ingredient1;
    }

    // Get and set second ingredient
    public int getIngredient2() {
        return ingredient2;
    }
    public void setIngredient2(int ingredient2) {
        this.ingredient2 = ingredient2;
    }

    // Get and set third ingredient
    public int getIngredient3() {
        return ingredient3;
    }
    public void setIngredient3(int ingredient3) {
        this.ingredient3 = ingredient3;
    }

    // Get full recipe
    public int[] getRecipe() {
        return recipe;
    }

    // Recipe Generation ---------------------------------------------------------------------------

    /**
     * Generates a recipe by creating three random ingredients in the range 1-20.
     * The generated ingredients are assigned to the recipe array and sorted in ascending order.
     *
     * @return The sorted array of ingredients representing the recipe.
     */
    public int[] generateRecipe() {
        Random random = new Random();

        // Generate three random numbers between 1 and 20 for ingredients
        ingredient1 = random.nextInt(20) + 1;
        ingredient2 = random.nextInt(20) + 1;
        ingredient3 = random.nextInt(20) + 1;

        // Assign ingredients to the recipe array and sort it
        recipe = new int[]{ingredient1, ingredient2, ingredient3};
        Arrays.sort(recipe);

        return recipe;
    }
}
