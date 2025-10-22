package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;

@Mixin(HandledScreen.class)
public interface IsPointOverSlotInvoker {
	@Invoker("isPointOverSlot")
	public boolean invokeIsPointOverSlot(Slot slot, double pointX, double pointY);
}
