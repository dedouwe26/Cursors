package oxded.cursors.api;

import net.minecraft.client.util.Window;

@FunctionalInterface
public interface WindowProvider {
    Window getWindow();
}
