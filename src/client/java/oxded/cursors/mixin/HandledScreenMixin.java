package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import oxded.cursors.CursorsClient;
import oxded.cursors.api.CursorType;

@Mixin(HandledScreen.class)
public class HandledScreenMixin<T extends ScreenHandler> {
	boolean state = false;
	@SuppressWarnings("unchecked")
	@Inject(method = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;render(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("RETURN"))
	private void init(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		HandledScreen<T> screen = (HandledScreen<T>)(Object)this;
		T handler = screen.getScreenHandler();
		for (int k = 0; k < handler.slots.size(); k++) {
			Slot slot = (Slot)handler.slots.get(k);
			if (((IsPointOverSlotInvoker)screen).invokeIsPointOverSlot(slot, mouseX, mouseY)) {
				if (slot.isEnabled()) {
					if (slot.hasStack()) {
						state = CursorsClient.changeCursor(CursorType.GRAB, state);
						return;
					}
				} else {
					state = CursorsClient.changeCursor(CursorType.BLOCKED, state);
					return;
				}
			}
		}
		state = CursorsClient.resetCursor(state);
	}
}
