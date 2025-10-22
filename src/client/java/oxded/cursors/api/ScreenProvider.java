package oxded.cursors.api;

import net.minecraft.client.gui.screen.Screen;

@FunctionalInterface
public interface ScreenProvider {
	Screen getCurrentScreen();
}
