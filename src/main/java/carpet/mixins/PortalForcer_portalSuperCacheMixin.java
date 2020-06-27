package carpet.mixins;

import carpet.CarpetSettings;
import carpet.utils.portalsearcher.PortalSearchResult;
import carpet.utils.portalsearcher.PortalSearcherAbstract;
import carpet.utils.portalsearcher.PortalSearcherSuperCache;
import carpet.utils.portalsearcher.SuperCacheHandler;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.world.PortalForcer;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.util.ObfuscationUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Mixin(PortalForcer.class)
public abstract class PortalForcer_portalSuperCacheMixin {
    private static final Class classTicketInfo;
    static {
        classTicketInfo = PortalForcer.class.getDeclaredClasses()[0];
    }
//
    @Shadow @Final
    private ServerWorld world;

    @Shadow @Final private static NetherPortalBlock PORTAL_BLOCK;

    @Shadow public abstract void tick(long long_1);

    private BlockPos blockPos;
//
    @Inject(method = "getPortal", at = @At("HEAD"))
    private void onCreateColumnPos(BlockPos entityPos, Vec3d vel, Direction lastPortalDirection, double directionX, double directionY, boolean isPlayer, CallbackInfoReturnable<BlockPattern.TeleportTarget> cir) {
        this.blockPos = entityPos;
    }
//
    @Redirect(method = "getPortal",
            at = @At(
            value = "INVOKE",
            target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
    ))
    private Object onGettingCache(Map map, Object key) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (!CarpetSettings.portalSuperCache) {
            return map.get(key);
        }
        Object ticketInfo = map.get(key);

        if (ticketInfo != null) {
            return ticketInfo;
        }
        PortalSearcherAbstract searcher = new PortalSearcherSuperCache(this.world);
        PortalSearchResult searchResult = searcher.search(this.blockPos);
        if (searchResult.getDistanceSq() >= 0.0D) {
            this.world.getChunkManager().addTicket(ChunkTicketType.PORTAL, new ChunkPos(searchResult.getResult()), 3, (ColumnPos) key);
            Constructor constructor = classTicketInfo.getConstructors()[0];
            constructor.setAccessible(true);
            ticketInfo = constructor.newInstance(searchResult.getResult(), world.getTime());
            map.put(key, ticketInfo);
            return ticketInfo;
        }
        return null;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onTeleporterTick(CallbackInfo ci) {
        if (!CarpetSettings.portalSuperCache) {
            (this.world.getDimension().isNether() ? SuperCacheHandler.getHandlerNether() : SuperCacheHandler.getHandlerOverworld()).clear();
        }
    }
}
