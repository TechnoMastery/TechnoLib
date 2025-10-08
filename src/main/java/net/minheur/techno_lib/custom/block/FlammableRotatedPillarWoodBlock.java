package net.minheur.techno_lib.custom.block;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FlammableRotatedPillarWoodBlock extends FlammableRotatedPillarBlock {
    public static Map<Block, RegistryObject<Block>> states = new HashMap<>();

    public FlammableRotatedPillarWoodBlock(Properties pProperties, RegistryObject<Block> pStrippedState) {
        super(pProperties, 5, 5);
        states.put(this, pStrippedState);
    }
    public FlammableRotatedPillarWoodBlock(Properties pProperties) {
        super(pProperties, 5, 5);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            for (Map.Entry<Block, RegistryObject<Block>> entry : states.entrySet()) {
                if (state.is(entry.getKey())) {
                    return entry.getValue().get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

}
