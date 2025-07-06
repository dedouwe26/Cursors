package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.widget.ClickableWidget;
import oxded.cursors.api.CursorType;
import oxded.cursors.api.Cursors;

@Mixin(ClickableWidget.class)
public class ClickableWidgetMixin {
    @Inject(method = "Lnet/minecraft/client/gui/widget/ClickableWidget;renderWidget(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        ClickableWidget widget = (ClickableWidget)(Object)this;
        if (widget.isHovered()) {
            Cursors.setCursorType(CursorType.POINTER);
        }
        if (!widget.isHovered()) {
            Cursors.setCursorType(CursorType.DEFAULT);
        }
    }
}
