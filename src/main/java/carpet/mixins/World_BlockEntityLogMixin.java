package carpet.mixins;

import carpet.logging.LoggerRegistry;
import carpet.logging.logHelpers.BlockEntityLogHelper;
import jdk.internal.jline.internal.Nullable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(World.class)
public abstract class World_BlockEntityLogMixin {

    @Shadow public abstract long getTime();
    @Shadow @Final public List<BlockEntity> blockEntities;

    @Shadow @Nullable
    public abstract BlockEntity getBlockEntity(BlockPos pos);

    @Inject(method = "setBlockEntity", at = @At("HEAD"))

    private void log(BlockPos pos, BlockEntity blockEntity, CallbackInfo ci){
        if (LoggerRegistry.__blockentity) {
            BlockEntityLogHelper.onSetBlockEntity(pos, blockEntity, getTime());
        }
    }

}
