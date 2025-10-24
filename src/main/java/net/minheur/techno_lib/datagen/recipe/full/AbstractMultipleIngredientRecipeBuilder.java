package net.minheur.techno_lib.datagen.recipe.full;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AbstractResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultipleIngredientRecipeBuilder extends AbstractResultRecipeBuilder {
    protected final List<Ingredient> ingredients = new ArrayList<>();

    public AbstractMultipleIngredientRecipeBuilder(String modid, String recipeName, ItemLike result, int count) {
        super(modid, recipeName, result, count);
    }

    public AbstractMultipleIngredientRecipeBuilder addIngredient(ItemLike itemLike) {
        Ingredient ingredient = Ingredient.of(itemLike);
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (Ingredient ingredient : ingredients) if (ingredient.isEmpty()) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleIngredientResult extends ResultRecipeResult {
        protected final List<Ingredient> ingredients;

        protected MultipleIngredientResult(ResourceLocation id, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId, List<Ingredient> ingredients) {
            super(id, result, count, advancement, advancementId);
            this.ingredients = ingredients;
        }
    }
}
