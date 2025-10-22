package oxded.cursors.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;

@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface WindowProvider {
	Window getWindow();
}
