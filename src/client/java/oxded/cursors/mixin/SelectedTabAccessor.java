package oxded.cursors.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;

@Mixin(CreativeInventoryScreen.class)
public interface SelectedTabAccessor {
    @Accessor("selectedTab")
    ItemGroup getSelectedTab();
}
