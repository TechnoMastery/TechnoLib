package net.minheur.techno_lib.custom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FlammableRotatedPillarBlock extends RotatedPillarBlock {
    private final int flammability;
    private final int fireSpread;

    public FlammableRotatedPillarBlock(Properties pProperties, int flammability, int fireSpread) {
        super(pProperties);
        this.flammability = flammability;
        this.fireSpread = fireSpread;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return fireSpread;
    }
}
