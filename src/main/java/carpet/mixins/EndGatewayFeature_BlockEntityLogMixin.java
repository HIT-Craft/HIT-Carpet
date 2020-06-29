package carpet.mixins;

import carpet.logging.LoggerRegistry;
import carpet.logging.logHelpers.BlockEntityLogHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.EndGatewayFeature;
import net.minecraft.world.gen.feature.EndGatewayFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(EndGatewayFeature.class)
public abstract class EndGatewayFeature_BlockEntityLogMixin {

    @Inject(method = "generate", at=@At("HEAD"))
    private void log(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, EndGatewayFeatureConfig endGatewayFeatureConfig, CallbackInfoReturnable<Boolean> cir){
        if (LoggerRegistry.__blockentity) {
            BlockEntityLogHelper.onExitEndGatewayGenerate(iWorld.getBlockEntity(blockPos), blockPos);
        }
    }
}
