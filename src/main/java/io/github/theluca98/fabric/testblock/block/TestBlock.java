package io.github.theluca98.fabric.testblock.block;

import io.github.theluca98.fabric.testblock.util.SimpleIntRange;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestBlock extends Block {

    public static SimpleIntRange RANGE = new SimpleIntRange(0, 1024);
    public static final IntProperty PROPERTY = IntProperty.of("model_value", RANGE.getMinValue(), RANGE.getMaxValue());
    public static Identifier ID = new Identifier("testblockmod", "test_block");

    public TestBlock() {
        super(FabricBlockSettings.copyOf(Blocks.BEDROCK));
        setDefaultState(getStateManager().getDefaultState().with(PROPERTY, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PROPERTY);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isCreativeLevelTwoOp() && player.getStackInHand(hand).isEmpty()) {
            int value = state.get(PROPERTY);
            if (player.isSneaking()) {
                value = RANGE.coerce(value - 1);
                world.setBlockState(pos, state.with(PROPERTY, value));
                player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 0.5F);
                player.sendMessage(new TranslatableText("message.testblockmod.state_minus", value), true);
            } else {
                value = RANGE.coerce(value + 1);
                world.setBlockState(pos, state.with(PROPERTY, value));
                player.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1.0F);
                player.sendMessage(new TranslatableText("message.testblockmod.state_plus", value), true);
            }
        }
        return ActionResult.SUCCESS;
    }

}
