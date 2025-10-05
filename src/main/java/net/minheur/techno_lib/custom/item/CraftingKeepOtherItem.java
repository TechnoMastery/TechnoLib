package net.minheur.techno_lib.custom.item;

import net.minecraft.world.item.ItemStack;

public class CraftingKeepOtherItem extends CraftingRemainingItem {
    private final ItemStack craftingRemainer;

    public CraftingKeepOtherItem(Properties pProperties, ItemStack craftingRemainer) {
        super(pProperties);
        this.craftingRemainer = craftingRemainer;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(craftingRemainer.getItem());
    }
}
