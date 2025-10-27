package net.minheur.techno_lib.datagen.recipe.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minheur.techno_lib.datagen.recipe.result.AResultsRecipeBuilder;

import java.util.List;

public abstract class AIngredientResultsRecipeBuilder extends AResultsRecipeBuilder {
    protected final Ingredient ingredient;

    public AIngredientResultsRecipeBuilder(String modid, String recipeName, Ingredient ingredient) {
        super(modid, recipeName);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || ingredient.isEmpty();
    }

    public static abstract class IngredientResult extends MultipleResultRecipeResult {
        protected final Ingredient ingredient;

        protected IngredientResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results, Ingredient ingredient) {
            super(id, advancement, advancementId, results);
            this.ingredient = ingredient;
        }
    }
}
