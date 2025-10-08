package net.minheur.techno_lib.custom.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public abstract class AbstractIngredientRecipe extends AbstractRecipe {
    protected final NonNullList<Ingredient> ingredients;

    protected AbstractIngredientRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients) {
        super(id);
        this.ingredients = ingredients;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }
}
