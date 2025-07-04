package oxded.cursors;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import oxded.cursors.api.Cursors;

public class CursorsClient implements ClientModInitializer {
	private static Window window;
	@Override
	public void onInitializeClient() {
		setWindow();
		Cursors.init(window);
	}

	private static void setWindow() {
		MinecraftClient client = MinecraftClient.getInstance();
		window = client.getWindow();
	}
	public static Window getWindow() {
		if (window == null) setWindow();
		return window;
	}
	
}