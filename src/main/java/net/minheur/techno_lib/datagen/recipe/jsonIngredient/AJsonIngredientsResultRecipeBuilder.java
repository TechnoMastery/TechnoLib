package net.minheur.techno_lib.datagen.recipe.jsonIngredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.builders.RecipeJsonObjectBuilder;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AJsonIngredientsResultRecipeBuilder extends AResultRecipeBuilder {
    protected final List<JsonObject> ingredients = new ArrayList<>();

    public AJsonIngredientsResultRecipeBuilder(String modid, String recipeName, JsonObject result) {
        super(modid, recipeName, result);
    }

    /**
     * Adding an ingredient to the list
     * @param ingredient the ingredient you want to add. Use {@link RecipeJsonObjectBuilder} to build.
     * @return the current recipe
     */
    public AJsonIngredientsResultRecipeBuilder addIngredient(JsonObject ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : ingredients) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class IngredientResult extends ResultRecipeResult {
        protected final List<JsonObject> ingredients;

        protected IngredientResult(ResourceLocation id, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> ingredients) {
            super(id, advancement, advancementId, result);
            this.ingredients = ingredients;
        }
    }
}
