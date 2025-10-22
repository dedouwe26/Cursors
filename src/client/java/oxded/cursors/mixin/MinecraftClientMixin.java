package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import oxded.cursors.CursorsClient;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
		@Inject(method = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("RETURN"))
		private void init(CallbackInfo ci) {
			CursorsClient.resetCursor(true);
		}
}
