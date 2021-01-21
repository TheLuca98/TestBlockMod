package io.github.theluca98.fabric.testblock;

import io.github.theluca98.fabric.testblock.block.TestBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

public class TestBlockMod implements ModInitializer {

    public static TestBlock TEST_BLOCK;
    public static BlockItem TEST_BLOCK_ITEM;

    @Override
    public void onInitialize() {
        TEST_BLOCK = Registry.register(
                Registry.BLOCK,
                TestBlock.ID,
                new TestBlock()
        );
        TEST_BLOCK_ITEM = Registry.register(
                Registry.ITEM,
                TestBlock.ID,
                new BlockItem(TEST_BLOCK, new FabricItemSettings())
        );
    }

}
