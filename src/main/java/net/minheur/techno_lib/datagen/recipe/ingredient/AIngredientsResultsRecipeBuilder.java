package net.minheur.techno_lib.datagen.recipe.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AResultsRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AIngredientsResultsRecipeBuilder extends AResultsRecipeBuilder {
    protected final List<Ingredient> ingredients = new ArrayList<>();

    public AIngredientsResultsRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    public AIngredientsResultsRecipeBuilder addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }
    public AIngredientsResultsRecipeBuilder addIngredient(ItemLike ingredient) {
        ingredients.add(Ingredient.of(ingredient));
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (Ingredient ingredient : ingredients) if (ingredient.isEmpty()) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class IngredientResult extends MultipleResultRecipeResult {
        protected final List<Ingredient> ingredients;

        protected IngredientResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results, List<Ingredient> ingredients) {
            super(id, advancement, advancementId, results);
            this.ingredients = ingredients;
        }
    }
}
