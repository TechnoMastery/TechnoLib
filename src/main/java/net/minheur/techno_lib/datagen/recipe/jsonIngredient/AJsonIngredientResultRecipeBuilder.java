package net.minheur.techno_lib.datagen.recipe.jsonIngredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.datagen.recipe.result.ASingleResultRecipeBuilder;

public abstract class AJsonIngredientResultRecipeBuilder extends ASingleResultRecipeBuilder {
    protected final JsonObject ingredient;

    public AJsonIngredientResultRecipeBuilder(String modid, String recipeName, JsonObject result, JsonObject ingredient) {
        super(modid, recipeName, result);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || ingredient.isJsonNull();
    }

    public static abstract class SingleJsonResult extends SingleResultRecipeResult {
        protected final JsonObject ingredient;

        protected SingleJsonResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, JsonObject result, JsonObject ingredient) {
            super(id, advancement, advancementId, result);
            this.ingredient = ingredient;
        }
    }
}
