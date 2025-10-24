package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AbstractResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AbstractMultipleJsonIngredientRecipeBuilder extends AbstractResultRecipeBuilder {
    protected final List<JsonObject> ingredients = new ArrayList<>();

    public AbstractMultipleJsonIngredientRecipeBuilder(String modid, String recipeName, ItemLike result, int count) {
        super(modid, recipeName, result, count);
    }

    public AbstractMultipleJsonIngredientRecipeBuilder addIngredient(ItemLike itemLike) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", getBuiltInItemRegistry(itemLike));
        ingredients.add(ingredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : ingredients) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonIngredientResult extends ResultRecipeResult {
        protected final List<JsonObject> ingredients;

        protected MultipleJsonIngredientResult(ResourceLocation id, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> ingredients) {
            super(id, result, count, advancement, advancementId);
            this.ingredients = ingredients;
        }
    }
}
