package oxded.cursors.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.Window;
import net.minecraft.util.Identifier;
import oxded.cursors.CursorsClient;

@Environment(EnvType.CLIENT)
public final class Cursors {
    private static final Identifier DEFAULT_CURSOR = Identifier.of("cursors", "textures/cursors/default.png");
    private static final Identifier POINTER_CURSOR = Identifier.of("cursors", "textures/cursors/pointer.png");
    private static final Identifier TEXT_CURSOR = Identifier.of("cursors", "textures/cursors/text.png");
    private static final Identifier GRAB_CURSOR = Identifier.of("cursors", "textures/cursors/grab.png");
    private static final Identifier GRABBING_CURSOR = Identifier.of("cursors", "textures/cursors/grabbing.png");
    private static final Identifier BLOCKED_CURSOR = Identifier.of("cursors", "textures/cursors/blocked.png");
	private static Window window;
    private static CursorType cursorType;
    private static AbstractTexture defaultCursor;
    private static AbstractTexture pointerCursor;
    private static AbstractTexture textCursor;
    private static AbstractTexture grabCursor;
    private static AbstractTexture grabbingCursor;
    private static AbstractTexture blockedCursor;

    public static void init(Window win, TextureManager textureManager) {
        window = win;
        defaultCursor = textureManager.getTexture(DEFAULT_CURSOR);
        pointerCursor = textureManager.getTexture(POINTER_CURSOR);
        textCursor = textureManager.getTexture(TEXT_CURSOR);
        grabCursor = textureManager.getTexture(GRAB_CURSOR);
        grabbingCursor = textureManager.getTexture(GRABBING_CURSOR);
        blockedCursor = textureManager.getTexture(BLOCKED_CURSOR);
    }

    public static Window getWindow() {
        if (window == null) CursorsClient.getWindow();
        return window;
    }

    public static void setCursorType(CursorType type) {
        cursorType = type;
    }
    public static CursorType getCursorType() {
        return cursorType;
    }
}
