package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minheur.techno_lib.datagen.recipe.result.ASingleResultRecipeBuilder;

public abstract class ASingleIngredientRecipeBuilder extends ASingleResultRecipeBuilder {
    protected final Ingredient ingredient;

    public ASingleIngredientRecipeBuilder(String modid, String recipeName, JsonObject result, Ingredient ingredient) {
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
