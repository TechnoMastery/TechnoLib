package net.minheur.techno_lib.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public abstract class AbstractSingleJsonResultRecipeBuilder extends AbstractRecipeBuilder {
    protected final JsonObject result = new JsonObject();

    public AbstractSingleJsonResultRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }

    public AbstractSingleJsonResultRecipeBuilder addResult(ItemLike result) {
        this.result.addProperty("item", getBuiltInItemRegistry(result));
        return this;
    }

    @Override
    protected boolean isRecipeEmpty() {
        return super.isRecipeEmpty() || result.isJsonNull();
    }

    public static abstract class SingleJsonResultRecipeResult extends BaseResult {
        protected final JsonObject result;

        protected SingleJsonResultRecipeResult(ResourceLocation id, Advancement.Builder advancement, ResourceLocation advancementId, JsonObject result) {
            super(id, advancement, advancementId);
            this.result = result;
        }
    }
}
