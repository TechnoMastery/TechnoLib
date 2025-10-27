package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AMultipleJsonIngredientRecipeBuilder extends AResultRecipeBuilder {
    protected final List<JsonObject> ingredients = new ArrayList<>();

    public AMultipleJsonIngredientRecipeBuilder(String modid, String recipeName, JsonObject result) {
        super(modid, recipeName, result);
    }

    /**
     * Adding an ingredient to the list
     * @param itemLike the ItemLike you want to add
     * @return the current recipe
     */
    public AMultipleJsonIngredientRecipeBuilder addIngredient(JsonObject ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : ingredients) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonIngredientResult extends ResultRecipeResult {
        protected final List<JsonObject> ingredients;

        protected MultipleJsonIngredientResult(ResourceLocation id, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> ingredients) {
            super(id, result, advancement, advancementId);
            this.ingredients = ingredients;
        }
    }
}
