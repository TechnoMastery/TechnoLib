package net.minheur.techno_lib.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.world.level.ItemLike;
import net.minheur.techno_lib.builders.JsonBuilder;

public interface ICreateSequenceRecipe {
      JsonObject getSequenceRecipe();

      default JsonObject getTransitional(ItemLike transitionalItem) {
          return JsonBuilder.json().addItem(transitionalItem).build();
      }
}
