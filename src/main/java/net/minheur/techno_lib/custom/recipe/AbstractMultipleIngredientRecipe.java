package net.minheur.techno_lib.custom.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public abstract class AbstractMultipleIngredientRecipe extends AbstractResultRecipe {
    protected final NonNullList<Ingredient> ingredients;

    protected AbstractMultipleIngredientRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack output) {
        super(id, output);
        this.ingredients = ingredients;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }
}
