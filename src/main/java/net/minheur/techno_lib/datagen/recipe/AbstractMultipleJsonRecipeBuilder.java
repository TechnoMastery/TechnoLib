package net.minheur.techno_lib.datagen.recipe;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMultipleJsonRecipeBuilder extends AbstractRecipeBuilder {
    protected final List<JsonObject> results = new ArrayList<>();

    public AbstractMultipleJsonRecipeBuilder(String modid, String recipeName) {
        super(modid, recipeName);
    }
}
