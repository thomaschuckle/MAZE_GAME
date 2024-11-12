package model;

import java.util.Arrays;
import java.util.Random;

public class Card {
    private int ingredient1;
    private int ingredient2;
    private int ingredient3;
    
    private int[] recipe = new int[3];

    public Card(int ingredient1, int ingredient2, int ingredient3) {
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
    }

    public int getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(int ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public int getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(int ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public int getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(int ingredient3) {
        this.ingredient3 = ingredient3;
    }
    
    public int[] getRecipe() {
        return recipe;
    }

    public int[] generateRecipe() {
        Random random = new Random();

        // Generate three random numbers in the range 1-20
        ingredient1 = random.nextInt(20) + 1;
        ingredient2 = random.nextInt(20) + 1;
        ingredient3 = random.nextInt(20) + 1;

        // Assign them to the recipe array and sort it in ascending order
        recipe = new int[]{ingredient1, ingredient2, ingredient3};
        Arrays.sort(recipe);

        return recipe;
    }
}
