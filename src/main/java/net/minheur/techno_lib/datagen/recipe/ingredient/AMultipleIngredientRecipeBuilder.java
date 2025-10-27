package net.minheur.techno_lib.datagen.recipe.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AMultipleIngredientRecipeBuilder extends AResultRecipeBuilder {
    protected final List<Ingredient> ingredients = new ArrayList<>();

    public AMultipleIngredientRecipeBuilder(String modid, String recipeName, JsonObject result) {
        super(modid, recipeName, result);
    }

    public AMultipleIngredientRecipeBuilder addIngredient(ItemLike itemLike) {
        Ingredient ingredient = Ingredient.of(itemLike);
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (Ingredient ingredient : ingredients) if (ingredient.isEmpty()) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleIngredientResult extends SingleResultRecipeResult {
        protected final List<Ingredient> ingredients;

        protected MultipleIngredientResult(ResourceLocation id, JsonObject result, Advancement.Builder advancement, ResourceLocation advancementId, List<Ingredient> ingredients) {
            super(id, advancement, advancementId, result);
            this.ingredients = ingredients;
        }
    }
}
