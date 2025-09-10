package net.minheur.techno_lib.custom.block.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minheur.techno_lib.TechnoLib;
import net.minheur.techno_lib.TechnoLibData;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
    DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TechnoLib.MOD_ID;)

    public static final RegistryObject<BlockEntityType<SimpleSignBlockEntity>> ALL_SIGNS =
            BLOCK_ENTITIES.register("all_signs", () ->
                    BlockEntityType.Builder.of(SimpleSignBlockEntity,
                            TechnoLibData.getAllSignBlocks()).build(null));

    public static final RegistryObject<BlockEntityType<SimpleHangingSignBlockEntity>> ALL_HANGING_SIGNS =
            BLOCK_ENTITIES.register("all_hanging_signs", () ->
                    BlockEntityType.Builder.of(SimpleHangingSignBlockEntity,
                            TechnoLibData.getAllHangingSignBlocks()).build(null));

}
