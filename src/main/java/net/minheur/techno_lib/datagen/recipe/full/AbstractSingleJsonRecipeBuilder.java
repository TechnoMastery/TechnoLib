package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AbstractSingleJsonResultRecipeBuilder;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AbstractSingleJsonRecipeBuilder extends AbstractSingleJsonResultRecipeBuilder {
    protected final JsonObject ingredient = new JsonObject();

    public AbstractSingleJsonRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    public AbstractSingleJsonRecipeBuilder addIngredient(ItemLike result) {
        this.ingredient.addProperty("item", getBuiltInItemRegistry(result));
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || ingredient.isJsonNull();
    }

    public static abstract class SingleJsonResult extends SingleJsonResultRecipeResult {
        protected final JsonObject ingredient;

        protected SingleJsonResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, JsonObject result, JsonObject ingredient) {
            super(id, advancement, advancementId, result);
            this.ingredient = ingredient;
        }
    }
}
