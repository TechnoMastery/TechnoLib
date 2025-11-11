package net.minheur.techno_lib.builders;

import net.minecraft.resources.ResourceLocation;
import net.minheur.techno_lib.datagen.advancement.AdvancementBuilder;
import net.minheur.techno_lib.datagen.advancement.CriterionBuilder;

public abstract class BaseAdvancements {
    private final String MOD_ID;
    protected static final ResourceLocation inventoryChanged = new ResourceLocation("minecraft", "inventory_changed");
    protected static final ResourceLocation placedBlock = new ResourceLocation("minecraft", "placed_block");

    public BaseAdvancements(String MOD_ID) {
        this.MOD_ID = MOD_ID;
    }

    /**
     * Adding a root advancement
     * @param id the id of your advancement
     * @param group the group of your advancement (ex. story, adventure...)
     * @return a new advancement builder
     */
    protected AdvancementBuilder addRoot(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id + "-root", group, true, MOD_ID);
    }
    /**
     * Adding an advancement with a parent
     * @param id the id of your advancement
     * @param group the group pf your advancement (ex. story, adventure...). It should be the same as the root !
     * @return the new advancement builder
     */
    protected AdvancementBuilder addWithParent(String id, String group) {
        if (id == null) throw new IllegalStateException("Can't have a null advancement id !");
        if (group == null) throw new IllegalStateException("Can't have a null advancement group !");
        return new AdvancementBuilder(id, group, false, MOD_ID);
    }
    /**
     * Creates a builder for criterion
     * @param trigger the id of the criterion's trigger
     * @return a new criterion builder
     */
    protected CriterionBuilder simpleCriterion(ResourceLocation trigger) {
        return new CriterionBuilder(trigger);
    }
}
