package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.datagen.recipe.result.ASingleJsonResultRecipeBuilder;

public abstract class ASingleJsonRecipeBuilder extends ASingleJsonResultRecipeBuilder {
    protected final JsonObject ingredient;

    public ASingleJsonRecipeBuilder(String modid, String recipeName, JsonObject result, JsonObject ingredient) {
        super(modid, recipeName, result);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || ingredient.isJsonNull();
    }

    public static abstract class SingleJsonResult extends SingleJsonResultRecipeResult {
        protected final JsonObject ingredient;

        protected SingleJsonResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, JsonObject result, JsonObject ingredient) {
            super(id, advancement, advancementId, result);
            this.ingredient = ingredient;
        }
    }
}
