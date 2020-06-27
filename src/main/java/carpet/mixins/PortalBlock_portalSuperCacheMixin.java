package carpet.mixins;

import carpet.utils.portalsearcher.SuperCacheHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetherPortalBlock.class)
public abstract class PortalBlock_portalSuperCacheMixin extends Block {

    public PortalBlock_portalSuperCacheMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        if (!world_1.isClient() && blockPos_1.getY() < world_1.getEffectiveHeight()) {
            (world_1.getDimension().isNether() ? SuperCacheHandler.getHandlerNether() : SuperCacheHandler.getHandlerOverworld()).addPortal(blockPos_1);
        }
    }

    @Override
    public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        if (!world_1.isClient() && blockPos_1.getY() < world_1.getEffectiveHeight()) {
            (world_1.getDimension().isNether() ? SuperCacheHandler.getHandlerNether() : SuperCacheHandler.getHandlerOverworld()).removePortal(blockPos_1);
        }
    }
}
