package net.minheur.techno_lib.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.world.level.ItemLike;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AbstractSingleJsonIngredientJsonResultsRecipeBuilder extends AbstractMultipleJsonResultRecipeBuilder {
    protected final JsonObject ingredient = new JsonObject();

    public AbstractSingleJsonIngredientJsonResultsRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    public AbstractSingleJsonIngredientJsonResultsRecipeBuilder addIngredient(ItemLike ingredient) {
        this.ingredient.addProperty("item", getBuiltInItemRegistry(ingredient));
        return this;
    }
}
