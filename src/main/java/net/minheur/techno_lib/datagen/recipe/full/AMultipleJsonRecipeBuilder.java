package net.minheur.techno_lib.datagen.recipe.full;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.result.AMultipleJsonResultRecipeBuilder;

import java.util.ArrayList;
import java.util.List;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AMultipleJsonRecipeBuilder extends AMultipleJsonResultRecipeBuilder {
    /**
     * The List of ingredient items
     */
    protected final List<JsonObject> ingredients = new ArrayList<>();

    public AMultipleJsonRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    /**
     * Adding an ingredient to the list
     * @param item the ItemLike you want to add
     * @return the current recipe
     */
    public AMultipleJsonRecipeBuilder addIngredient(ItemLike item) {
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", getBuiltInItemRegistry(item));
        ingredients.add(ingredient);
        return this;
    }
    /**
     * Adding a fluid to the ingredient list
     * @param fluid the fluid ID you want to add (ex. {@code minecraft:lava})
     * @param amount quantity of fluid needed (in mB)
     * @return the current recipe
     */
    public AMultipleJsonRecipeBuilder addFluidIngredient(String fluid, int amount) {
        JsonObject fluidIngredient = new JsonObject();
        fluidIngredient.addProperty("fluid", fluid);
        fluidIngredient.addProperty("amount", amount);
        ingredients.add(fluidIngredient);
        return this;
    }

    /**
     * Adding a whole tag to the ingredients
     * @param tag the tag you want to add
     * @return the current recipe
     */
    public AMultipleJsonRecipeBuilder addTagIngredient(TagKey<Item> tag) {
        JsonObject tagIngredient = new JsonObject();
        tagIngredient.addProperty("tag", tag.location().toString());
        ingredients.add(tagIngredient);
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        for (JsonObject object : ingredients) if (object == null) return true;
        return super.isRecipeEmpty();
    }

    public static abstract class MultipleJsonResult extends MultipleJsonResultRecipeResult {
        /**
         * The recipe ingredients list
         */
        protected final List<JsonObject> ingredients;

        protected MultipleJsonResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, List<JsonObject> results, List<JsonObject> ingredients) {
            super(id, advancement, advancementId, results);
            this.ingredients = ingredients;
        }
    }
}
