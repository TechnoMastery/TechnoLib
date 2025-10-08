package net.minheur.techno_lib.custom.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minheur.techno_lib.custom.SimpleEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMenuEnergyBlockEntity extends AbstractMenuBlockEntity {

    public final SimpleEnergyStorage energyStorage;
    private LazyOptional<IEnergyStorage> energyCap;

    public AbstractMenuEnergyBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slotAmount,
                                         int capacity, int maxInputPerTick, int maxOutputPerTick) {
        super(pType, pPos, pBlockState, slotAmount);
        this.energyStorage = new SimpleEnergyStorage(capacity, maxInputPerTick, maxOutputPerTick, this::onEnergyChange);
        this.energyCap = LazyOptional.of(() -> energyStorage);
    }

    public AbstractMenuEnergyBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slotAmount, ContainerData data, int capacity) {
        this(pType, pPos, pBlockState, slotAmount, capacity, capacity, capacity);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) return energyCap.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.energyCap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyCap.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        energyCap = LazyOptional.of(() -> energyStorage);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("energy", energyStorage.getEnergyStored());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        energyStorage.setEnergy(pTag.getInt("energy"));
    }

    private void onEnergyChange() {
        if (this.level != null && !this.level.isClientSide()) setChanged();
    }
}
