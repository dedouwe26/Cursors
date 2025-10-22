package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;

@Mixin(CreativeInventoryScreen.class)
public interface GetTabInvoker {
	@Invoker("getTabX")
	int invokeGetTabX(ItemGroup group);
	@Invoker("getTabY")
	int invokeGetTabY(ItemGroup group);
}
