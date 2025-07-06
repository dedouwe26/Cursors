package oxded.cursors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import oxded.cursors.api.Cursors;

@Environment(EnvType.CLIENT)
public class CursorsClient implements ClientModInitializer {
	public static final String MOD_ID = "cursors";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static Window window;
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing");
		setWindow();
		Cursors.init(window, CursorResources.INSTANCE);
	}

	private static void setWindow() {
		MinecraftClient client = MinecraftClient.getInstance();
		window = client.getWindow();
	}
}