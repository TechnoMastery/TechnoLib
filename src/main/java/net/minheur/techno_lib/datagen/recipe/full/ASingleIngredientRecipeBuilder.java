package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

public abstract class ASingleIngredientRecipeBuilder extends AResultRecipeBuilder {
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

    public static abstract class SingleIngredientResult extends ResultRecipeResult {
        protected final Ingredient ingredient;

        protected SingleIngredientResult(ResourceLocation id, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId, Ingredient ingredient) {
            super(id, result, advancement, advancementId);
            this.ingredient = ingredient;
        }
    }
}
