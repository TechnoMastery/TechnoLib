package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AMultipleJsonResultRecipeBuilder;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class ASingleJsonIngredientJsonResultsRecipeBuilder extends AMultipleJsonResultRecipeBuilder {
    protected final JsonObject ingredient = new JsonObject();

    public ASingleJsonIngredientJsonResultsRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    public ASingleJsonIngredientJsonResultsRecipeBuilder addIngredient(ItemLike ingredient) {
        this.ingredient.addProperty("item", getBuiltInItemRegistry(ingredient));
        return this;
    }
}
