package net.minheur.techno_lib.custom.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CraftingRemainingItem extends Item {
    public CraftingRemainingItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }
}
