package net.minheur.techno_lib.builders;

import com.google.gson.JsonObject;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import static net.minheur.techno_lib.Utils.getBuiltInItemRegistry;

public class JsonBuilder {
    private final JsonObject object;

    public JsonBuilder() {
        this.object = new JsonObject();
    }

    public static JsonBuilder json() {
        return new JsonBuilder();
    }

    public JsonBuilder addItem(ItemLike item) {
        object.addProperty("item", getBuiltInItemRegistry(item));
        return this;
    }
    public JsonBuilder addFluid(String fluid, int amount) {
        object.addProperty("fluid", fluid);
        object.addProperty("amount", amount);
        return this;
    }

    /**
     * This uses Create's potion system, so it will NOT work other ways.
     * @param potionName formated as {@code namespace:potion}
     * @param amount of the potion
     * @return the current builder
     */
    public JsonBuilder addPotion(String potionName, int amount) {
        JsonObject nbt = new JsonObject();
        nbt.addProperty("Bottle", "REGULAR");
        nbt.addProperty("Potion", potionName);

        object.addProperty("fluid", "create:potion");
        object.addProperty("amount", amount);
        object.add("nbt", nbt);
        return this;
    }
    public JsonBuilder addTag(TagKey<Item> tag) {
        object.addProperty("tag", tag.location().toString());
        return this;
    }

    public JsonBuilder addCount(int count) {
        if (count > 1) object.addProperty("count", count);
        return this;
    }
    public JsonBuilder addChance(float chance) {
        if ((chance > 0) && !(chance >= 1)) object.addProperty("chance", chance);
        return this;
    }

    public JsonBuilder addRecipeNBT(RecipeNbtBuilder nbt) {
        object.add("nbt", nbt.getPropertiesJson());
        return this;
    }

    public JsonObject build() {
        return object;
    }
}
