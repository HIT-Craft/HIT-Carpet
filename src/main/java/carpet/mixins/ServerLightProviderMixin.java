package carpet.mixins;

import carpet.CarpetSettings;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.world.chunk.light.LevelPropagator;
import net.minecraft.world.chunk.light.LightStorage;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLightingProvider.class)
public class ServerLightProviderMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void cancelcall1(CallbackInfo ci) {
        if (!CarpetSettings.lightUpdates){
            ci.cancel();
        }
    }

    @Inject(method = "runTasks", at = @At("HEAD"))
    private void say(CallbackInfo ci){
        System.out.println("cal");
    }

    @Inject(method = "enqueue(IILjava/util/function/IntSupplier;Lnet/minecraft/server/world/ServerLightingProvider$class_3901;Ljava/lang/Runnable;)V", at = @At("HEAD"), cancellable = true)
    private void cancelcall2(CallbackInfo ci) {
        if (!CarpetSettings.lightUpdates){
            ci.cancel();
        }
    }
}
