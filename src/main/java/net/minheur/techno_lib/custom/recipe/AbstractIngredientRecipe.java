package net.minheur.techno_lib.custom.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public abstract class AbstractIngredientRecipe extends AbstractBaseRecipe {
    protected final NonNullList<Ingredient> ingredients;

    protected AbstractIngredientRecipe(ItemStack output, ResourceLocation id, NonNullList<Ingredient> ingredients) {
        super(output, id);
        this.ingredients = ingredients;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }
}
