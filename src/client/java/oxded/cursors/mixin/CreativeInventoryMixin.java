package oxded.cursors.mixin;

import java.util.Iterator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import oxded.cursors.CursorsClient;
import oxded.cursors.api.CursorType;

@Mixin(CreativeInventoryScreen.class)
public class CreativeInventoryMixin {
	boolean state = false;
	@Inject(method = "Lnet/minecraft/client/gui/screen/ingame/CreativeInventoryScreen;render(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("RETURN"))
	private void init(DrawContext ctx, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		CreativeInventoryScreen screen = (CreativeInventoryScreen)(Object)this;
		Iterator<ItemGroup> groups = ItemGroups.getGroupsToDisplay().iterator();
		while(groups.hasNext()) {
			ItemGroup itemGroup = groups.next();
			int i = ((GetTabInvoker)screen).invokeGetTabX(itemGroup);
			int j = ((GetTabInvoker)screen).invokeGetTabY(itemGroup);
			if (((IsPointWithinBoundsInvoker)screen).invokeIsPointWithinBounds(i + 3, j + 3, 21, 27, mouseX, mouseY)) {
				state = CursorsClient.changeCursor(CursorType.POINTER, state);
				return;
			}
		}
		if (((SelectedTabAccessor)screen).getSelectedTab().hasScrollbar()) {
			if (((IsClickInScrollbarInvoker)screen).invokeIsClickInScrollbar(mouseX, mouseY)) {
				state = CursorsClient.changeCursor(CursorType.POINTER, state);
				return;
			}
		}
		state = CursorsClient.resetCursor(state);
	}
}
