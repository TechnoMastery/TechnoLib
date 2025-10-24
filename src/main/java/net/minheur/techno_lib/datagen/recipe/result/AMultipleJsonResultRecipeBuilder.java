package net.minheur.techno_lib.datagen.recipe.result;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import com.google.gson.JsonObject;
import net.minheur.techno_lib.datagen.recipe.ARecipeBuilder;

import java.util.ArrayList;
import java.util.List;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AMultipleJsonResultRecipeBuilder extends ARecipeBuilder {
    protected final List<JsonObject> results = new ArrayList<>();

    public AMultipleJsonResultRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    /**
     * Adding a result to your recipe.
     * @param result the result item
     * @param count the amount of result
     * @param chance the chance of getting the result
     * @return the current recipe
     */
    public AMultipleJsonResultRecipeBuilder addResult(JsonObject result) {
        results.add(result);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : results) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonResultRecipeResult extends BaseResult {
        /**
         * The recipe results list
         */
        protected final List<JsonObject> results;

        protected MultipleJsonResultRecipeResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results) {
            super(id, advancement, advancementId);
            this.results = results;
        }
    }
}
