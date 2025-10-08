package net.minheur.techno_lib.custom.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractResultRecipe extends AbstractRecipe {
    protected final ItemStack output;

    protected AbstractResultRecipe(ResourceLocation id, ItemStack output) {
        super(id);
        this.output = output;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }
}
