package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import oxded.cursors.CursorsClient;
import oxded.cursors.api.CursorType;

@Mixin(ClickableWidget.class)
public class ClickableWidgetMixin {
	boolean state = false;
	@Inject(method = "Lnet/minecraft/client/gui/widget/ClickableWidget;render(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("RETURN"))
	private void init(CallbackInfo ci) {
		ClickableWidget widget = (ClickableWidget)(Object)this;
		if (widget.isHovered()) {
			CursorType type = CursorType.POINTER;
			if (widget instanceof TextFieldWidget) {
				type = CursorType.TEXT;
			} else if (!widget.active) {
				type = CursorType.BLOCKED;
			}
			state = CursorsClient.changeCursor(type, state);
			
		} else {
			state = CursorsClient.resetCursor(state);
		}
	}
}
