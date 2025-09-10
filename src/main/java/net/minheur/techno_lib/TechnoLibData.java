package net.minheur.techno_lib;

import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.List;

public class TechnoLibData {
    private static List<Block> allSignBlocks;
    private static List<Block> allHangingSignBlocks;

    public static void addSignBlocks(Block... pBlocks) {
        allSignBlocks.addAll(Arrays.stream(pBlocks).toList());
    }
    public static void addHangingSignBlocks(Block... pBlocks) {
        allHangingSignBlocks.addAll(Arrays.stream(pBlocks).toList());
    }

    public static List<Block> getAllSignBlocks() {
        return allSignBlocks;
    }
    public static List<Block> getAllHangingSignBlocks() {
        return allHangingSignBlocks;
    }

}
