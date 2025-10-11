package net.minheur.techno_lib.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public abstract class AbstractRecipeBuilder {
    protected final String modid;
    protected final String recipeName;
    protected final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public AbstractRecipeBuilder(String modid, String recipeName) {
        this.modid = modid;
        if (recipeName.toLowerCase().replaceAll("\\s+", "").isEmpty()) throw new IllegalArgumentException("This name isn't valid !");
        this.recipeName = recipeName;
    }

    public AbstractRecipeBuilder unlocks(String key, CriterionTriggerInstance pCriterion) {
        this.advancement.addCriterion(key, pCriterion);
        return this;
    }

    private void ensureValid(ResourceLocation pId) {
        if (isRecipeEmpty()) throw new IllegalStateException("Invalid recipe for " + recipeName + " recipe " + pId + "!");
        if (this.advancement.getCriteria().isEmpty()) throw new IllegalStateException("No way of obtaining recipe "+ pId);
    }

    /**
     * Use : {@code return super.isRecipeEmpty() || [your condition]}
     * @return if the recipe is correct
     */
    protected boolean isRecipeEmpty() {
        return false;
    }

    protected ResourceLocation getFullRecipeId(ResourceLocation id) {
        return id.withPrefix(this.recipeName + "/");
    }
    protected ResourceLocation getFullAdvancementId(ResourceLocation id) {
        return id.withPrefix("recipes/" + this.recipeName + "/");
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        saveRecipeResult(consumer, id);
    }
    public void save(Consumer<FinishedRecipe> consumer, String id) {
        save(consumer, new ResourceLocation(modid, id));
    }

    /**
     * Here create a {@link BaseResult}.
     * <p> ex. {@code consumer.accept(new Result(recipePath, [...], advancement, advancementPath))}
     * <p> Replace {@code [...]} with your data (ex. ingredient...)
     * <p> /!\ There can also be some AFTER {@code advancementPath} !
     */
    protected abstract void saveRecipeResult(Consumer<FinishedRecipe> consumer, ResourceLocation id);

    public static abstract class BaseResult implements FinishedRecipe {
        protected final ResourceLocation id;
        protected final Advancement.Builder advancement;
        protected final ResourceLocation advancementId;

        protected BaseResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
