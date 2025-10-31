package net.minheur.techno_lib.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;

public interface ICreateSequenceRecipe {
      FinishedRecipe getFinishedRecipe();

      default JsonObject getSequencedRecipe() {
          return getFinishedRecipe().serializeRecipe();
      }
}
