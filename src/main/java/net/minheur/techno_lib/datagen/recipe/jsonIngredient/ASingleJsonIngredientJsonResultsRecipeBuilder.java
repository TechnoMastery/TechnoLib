package net.minheur.techno_lib.datagen.recipe.jsonIngredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.datagen.recipe.result.AMultipleResultRecipeBuilder;

import java.util.List;

public abstract class ASingleJsonIngredientJsonResultsRecipeBuilder extends AMultipleResultRecipeBuilder {
    protected final JsonObject ingredient;

    public ASingleJsonIngredientJsonResultsRecipeBuilder(String modid, String recipeName, JsonObject ingredient) {
        super(modid, recipeName);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || ingredient.isJsonNull();
    }

    public static abstract class SingleJsonIngredientResultsResult extends MultipleResultRecipeResult {
        protected final JsonObject ingredient;

        protected SingleJsonIngredientResultsResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results, JsonObject ingredient) {
            super(id, advancement, advancementId, results);
            this.ingredient = ingredient;
        }
    }
}
