package net.minheur.techno_lib.datagen.recipe.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

public abstract class AIngredientResultRecipeBuilder extends AResultRecipeBuilder {
    protected final Ingredient ingredient;

    public AIngredientResultRecipeBuilder(String modid, String recipeName, JsonObject result, Ingredient ingredient) {
        super(modid, recipeName, result);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                ingredient.isEmpty();
    }

    public static abstract class SingleIngredientResult extends SingleResultRecipeResult {
        protected final Ingredient ingredient;

        protected SingleIngredientResult(ResourceLocation id, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId, Ingredient ingredient) {
            super(id, advancement, advancementId, result);
            this.ingredient = ingredient;
        }
    }
}
