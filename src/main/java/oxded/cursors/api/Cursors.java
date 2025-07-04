package oxded.cursors.api;

import net.minecraft.client.util.Window;
import oxded.cursors.CursorsClient;

public final class Cursors {
	private static Window window;
    private static ResourceLocation location;
    public static void init(Window win) {
        window = win;
    }

    public static Window getWindow() {
        if (window == null) CursorsClient.getWindow();
        return window;
    }

    public static void setCursorType(CursorType type) {
        
    }
}
