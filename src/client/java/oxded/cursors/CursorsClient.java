package oxded.cursors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import oxded.cursors.api.CursorType;
import oxded.cursors.api.Cursors;

public class CursorsClient implements ClientModInitializer {
	private static MinecraftClient client;
	public static final String MOD_ID = "cursors";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static Window window;
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing");
		client = MinecraftClient.getInstance();
		CursorResources.init();
		Cursors.init(CursorResources.INSTANCE, () -> getWindow(), () -> getCurrentScreen());
	}
	private static void setWindow() {
		window = client.getWindow();
	}
	public static Window getWindow() {
		setWindow();
		return window;
	}
	public static MinecraftClient getClient() {
		return client;
	}
	public static Screen getCurrentScreen() {
		return getClient().currentScreen;
	}
	public static boolean resetCursor(boolean state) {
		if (Cursors.isCarryingItem()) {
			if (Cursors.getCursorType() != CursorType.GRABBING) {
				Cursors.setCursorType(CursorType.GRABBING);
			}
			return false;
		} else {
			if (Cursors.getCursorType() == CursorType.GRABBING) {
				Cursors.setCursorType(CursorType.DEFAULT);
			} else if (Cursors.getCursorType() != CursorType.DEFAULT && state) {
				Cursors.setCursorType(CursorType.DEFAULT);
			}
			return false;
		}
	}
	public static boolean changeCursor(CursorType type, boolean state) {
		if (Cursors.isCarryingItem()) {
			if (Cursors.getCursorType() != CursorType.GRABBING) {
				Cursors.setCursorType(CursorType.GRABBING);
			}
			return false;
		} else {
			if (Cursors.getCursorType() != type && !state) {
				Cursors.setCursorType(type);
				return true;
			}
			return state;
		}
	}
}