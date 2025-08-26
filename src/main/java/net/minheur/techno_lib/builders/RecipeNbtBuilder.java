package net.minheur.techno_lib.builders;

import com.google.gson.JsonObject;

/**
 * The builder for adding NBT to properties
 */
public class RecipeNbtBuilder {
    /**
     * The object containing properties
     */
    private final JsonObject properties = new JsonObject();

    public static RecipeNbtBuilder getNbt() {
        return new RecipeNbtBuilder();
    }

    /**
     * Add a {@link String} nbt
     * @param key the name of the nbt
     * @param value the value to assign
     * @return the current {@link RecipeNbtBuilder}
     */
    public RecipeNbtBuilder addNbt(String key, String value) {
        properties.addProperty(key, value);
        return this;
    }

    /**
     * Add a {@link Integer} nbt
     * @param key the name of the nbt
     * @param value the value to assign
     * @return the current {@link RecipeNbtBuilder}
     */
    public RecipeNbtBuilder addNbt(String key, int value) {
        properties.addProperty(key, value);
        return this;
    }

    /**
     * Get the property Json
     * @return the json you built
     */
    public JsonObject getPropertiesJson() {
        return properties;
    }

}
