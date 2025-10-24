package net.minheur.techno_lib.datagen.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AbstractMultipleJsonResultRecipeBuilder extends AbstractRecipeBuilder {
    protected final List<JsonObject> results = new ArrayList<>();

    public AbstractMultipleJsonResultRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    /**
     * Adding a result to your recipe.
     * @param result the result item
     * @param count the amount of result
     * @param chance the chance of getting the result
     * @return the current recipe
     */
    public AbstractMultipleJsonResultRecipeBuilder addResult(ItemLike result, int count, float chance) {
        JsonObject json = new JsonObject();
        json.addProperty("item", getBuiltInItemRegistry(result));
        if (count > 1) json.addProperty("count", count);
        if ((chance > 0) && !(chance >= 1)) json.addProperty("chance", chance);
        results.add(json);
        return this;
    }

    /**
     * Adding a result to your recipe
     * @param item the result you want
     * @param count the amount you want
     * @return the current recipe
     */
    public AbstractMultipleJsonResultRecipeBuilder addResult(ItemLike item, int count) {
        return addResult(item, count, 1f);
    }
    /**
     * Adding a result to your recipe. Count is 1
     * @param item the result you want
     * @return the current recipe
     */
    public AbstractMultipleJsonResultRecipeBuilder addResult(ItemLike item) {
        return this.addResult(item, 1, 1f);
    }
    /**
     * Adding a fluid result
     * @param fluid the fluis you want (ex. {@code minecraft:lava})
     * @param amount quantity of fluid given
     * @return the current recipe
     */
    public AbstractMultipleJsonResultRecipeBuilder addFluidResult(String fluid, int amount) {
        com.google.gson.JsonObject fluidResult = new com.google.gson.JsonObject();
        fluidResult.addProperty("fluid", fluid);
        fluidResult.addProperty("amount", amount);
        results.add(fluidResult);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : results) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonResultRecipeResult extends BaseResult {
        /**
         * The recipe results list
         */
        protected final List<JsonObject> results;

        protected MultipleJsonResultRecipeResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results) {
            super(id, advancement, advancementId);
            this.results = results;
        }
    }
}
