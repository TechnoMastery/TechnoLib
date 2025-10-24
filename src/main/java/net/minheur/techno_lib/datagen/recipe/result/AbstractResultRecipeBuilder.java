package net.minheur.techno_lib.datagen.recipe.result;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.datagen.recipe.AbstractRecipeBuilder;

public abstract class AbstractResultRecipeBuilder extends AbstractRecipeBuilder {
    protected final ItemLike result;
    protected final int count;

    public AbstractResultRecipeBuilder(String modid, String recipeName, ItemLike result, int count) {
        super(modid, recipeName);
        this.result = result;
        this.count = count;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() ||
                result == null || count <= 0;
    }

    public static abstract class ResultRecipeResult extends BaseResult {
        protected final ItemLike result;
        protected final int count;

        protected ResultRecipeResult(ResourceLocation id, ItemLike result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            super(id, advancement, advancementId);
            this.result = result;
            this.count = count;
        }
    }
}
