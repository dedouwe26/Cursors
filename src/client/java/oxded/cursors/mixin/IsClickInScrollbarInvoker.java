package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;

@Mixin(CreativeInventoryScreen.class)
public interface IsClickInScrollbarInvoker {
	@Invoker("isClickInScrollbar")
	boolean invokeIsClickInScrollbar(double mouseX, double mouseY);
}
