package carpet.mixins;

import carpet.fakes.ChunkTicketManagerInterface;
import com.mojang.datafixers.DataFixer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureManager;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Mixin(ServerChunkManager.class)
public class ServerChunkManager_ticketLogHelperMixin {
    @Shadow @Final private ChunkTicketManager ticketManager;

    @Inject(
            method = "<init>",
            at = @At(value = "RETURN")
    )
    private void onConstructed(ServerWorld serverWorld, File file, DataFixer dataFixer, StructureManager structureManager, Executor workerExecutor, ChunkGenerator<?> chunkGenerator, int i, WorldGenerationProgressListener worldGenerationProgressListener, Supplier<PersistentStateManager> supplier, CallbackInfo ci)
    {
        ((ChunkTicketManagerInterface)this.ticketManager).setServerWorld(serverWorld);
    }
}

