package net.minheur.techno_lib.datagen.recipe.full;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AbstractResultRecipeBuilder;

public abstract class AbstractSingleIngredientRecipeBuilder extends AbstractResultRecipeBuilder {
    protected final Ingredient ingredient;

    public AbstractSingleIngredientRecipeBuilder(String modid, String recipeName, ItemLike result, int count, Ingredient ingredient) {
        super(modid, recipeName, result, count);
        this.ingredient = ingredient;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                ingredient.isEmpty();
    }

    public static abstract class SingleIngredientResult extends ResultRecipeResult {
        protected final Ingredient ingredient;

        protected SingleIngredientResult(ResourceLocation id, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId, Ingredient ingredient) {
            super(id, result, count, advancement, advancementId);
            this.ingredient = ingredient;
        }
    }
}
