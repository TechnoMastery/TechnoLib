package net.minheur.techno_lib.datagen.recipe.result;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.datagen.recipe.ARecipeBuilder;

public abstract class ASingleResultRecipeBuilder extends ARecipeBuilder {
    protected final JsonObject result;

    public ASingleResultRecipeBuilder(String modid, String recipeName, JsonObject result) {
        super(modid, recipeName);
        this.result = result;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || result.isJsonNull();
    }

    public static abstract class SingleResultRecipeResult extends BaseResult {
        protected final JsonObject result;

        protected SingleResultRecipeResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, JsonObject result) {
            super(id, advancement, advancementId);
            this.result = result;
        }
    }
}
