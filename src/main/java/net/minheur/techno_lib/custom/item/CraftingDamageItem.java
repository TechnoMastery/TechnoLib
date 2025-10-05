package net.minheur.techno_lib.custom.item;

import net.minecraft.world.item.ItemStack;

public class CraftingDamageItem extends CraftingRemainingItem {
    public CraftingDamageItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack returnValue = new ItemStack(this);
        returnValue.setDamageValue(itemStack.getDamageValue() + 1);
        if(returnValue.getDamageValue() >= returnValue.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return returnValue;
    }
}
