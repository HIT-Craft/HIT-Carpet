package carpet.mixins;

import carpet.fakes.ChunkTicketManagerInterface;
import carpet.logging.LoggerRegistry;
import carpet.logging.logHelpers.ChunkTicketLogHelper;
import net.minecraft.server.world.ChunkTicket;
import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;

@Mixin(ChunkTicketManager.class)
public class ChunkTicketManager_ticketLogHelperMixin implements ChunkTicketManagerInterface {
    private ServerWorld world;

    public void setServerWorld(ServerWorld world)
    {
        this.world = world;
    }

    @Inject(
            method = "Lnet/minecraft/server/world/ChunkTicketManager;addTicket(JLnet/minecraft/server/world/ChunkTicket;)V",
            at = @At(value = "HEAD")
    )
    private void onAddTicket(long position, ChunkTicket<?> ticket, CallbackInfo ci) {
        if (LoggerRegistry.__ticket) {
            ChunkTicketLogHelper.onAddTicket(this.world, position, ticket);
        }
    }

    @Inject(
            method = "Lnet/minecraft/server/world/ChunkTicketManager;removeTicket(JLnet/minecraft/server/world/ChunkTicket;)V",
            at = @At(value = "HEAD")
    )
    private void onRemoveTicket(long position, ChunkTicket<?> chunkTicket, CallbackInfo ci)
    {
        if (LoggerRegistry.__ticket) {
            ChunkTicketLogHelper.onRemoveTicket(this.world, position, chunkTicket);
        }
    }

}
