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

    public JsonObject build() {
        return object;
    }
}
