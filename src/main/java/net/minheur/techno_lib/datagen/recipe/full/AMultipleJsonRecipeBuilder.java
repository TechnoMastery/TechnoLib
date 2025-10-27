package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.builders.RecipeJsonObjectBuilder;
import net.minheur.techno_lib.datagen.recipe.result.AMultipleJsonResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AMultipleJsonRecipeBuilder extends AMultipleJsonResultRecipeBuilder {
    /**
     * The List of ingredient items
     */
    protected final List<JsonObject> ingredients = new ArrayList<>();

    public AMultipleJsonRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    /**
     * Adding an ingredient to the list
     * @param ingredient the json you want to add. Use {@link RecipeJsonObjectBuilder} to build.
     * @return the current recipe
     */
    public AMultipleJsonRecipeBuilder addIngredient(JsonObject ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : ingredients) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonResult extends MultipleJsonResultRecipeResult {
        /**
         * The recipe ingredients list
         */
        protected final List<JsonObject> ingredients;

        protected MultipleJsonResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results, List<JsonObject> ingredients) {
            super(id, advancement, advancementId, results);
            this.ingredients = ingredients;
        }
    }
}
