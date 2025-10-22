package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.screen.ingame.HandledScreen;

@Mixin(HandledScreen.class)
public interface IsPointWithinBoundsInvoker {
	@Invoker("isPointWithinBounds")
	boolean invokeIsPointWithinBounds(int x, int y, int width, int height, double pointX, double pointY);
}
